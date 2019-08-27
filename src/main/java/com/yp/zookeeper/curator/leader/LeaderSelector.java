package com.yp.zookeeper.curator.leader;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.yp.zookeeper.curator.locks.InterProcessMutex;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.utils.CloseableExecutorService;
import org.apache.curator.utils.PathUtils;
import org.apache.curator.utils.ThreadUtils;
import org.apache.zookeeper.KeeperException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class LeaderSelector implements Closeable {
    @Override
    public void close() throws IOException {

    }
    /*private static final ThreadFactory DEFAULT_THREAD_FACTORY = ThreadUtils.newThreadFactory("LeaderSelector");

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final CuratorFramework client;
    private final LeaderSelectorListener listener;
    private final CloseableExecutorService executorService;
    private final InterProcessMutex mutex;
    private final AtomicReference<State> state = new AtomicReference<>(State.LATENT);
    private final AtomicBoolean autoRequeue = new AtomicBoolean(false);
    private final AtomicReference<Future<?>> ourTask = new AtomicReference<>(null);
    private volatile boolean hasLeadership;
    private volatile String id = "";
    private boolean isQueued = false;
    volatile CountDownLatch debugLeadershipLatch;
    volatile CountDownLatch debugLeadershipWaitLatch;
    volatile AtomicInteger failedMutexReleaseCount;
    private enum State {
        LATENT, STARTED, CLOSED
    }

    public LeaderSelector(CuratorFramework client, String leaderPath, LeaderSelectorListener listener) {
        this(client, leaderPath, new CloseableExecutorService(Executors.newSingleThreadExecutor(DEFAULT_THREAD_FACTORY), true), listener);
    }

    public LeaderSelector(CuratorFramework client, String leaderPath, ExecutorService executorService, LeaderSelectorListener listener) {
        this(client, leaderPath, new CloseableExecutorService(executorService), listener);
    }

    public LeaderSelector(CuratorFramework client, String leaderPath, CloseableExecutorService executorService, LeaderSelectorListener listener) {
        Preconditions.checkNotNull(client, "client cannot be null");
        PathUtils.validatePath(leaderPath);
        Preconditions.checkNotNull(listener, "listener cannot be null");
        this.client = client;
        this.listener = new WrappedListener(this, listener);
        hasLeadership = false;
        this.executorService = executorService;
        mutex = new InterProcessMutex();//TODO-yepan lock implements
    }

    @Override
    public synchronized void close() throws IOException {
        Preconditions.checkState(state.compareAndSet(State.STARTED, State.CLOSED), "Already closed or has not been started");
        client.getConnectionStateListenable().removeListener(listener);
        executorService.close();
        ourTask.set(null);
    }

    static byte[] getIdBytes(String id) {
        return id.getBytes(StandardCharsets.UTF_8);
    }

    public void autoRequeue() {
        autoRequeue.set(true);
    }

    public void setId(String id) {
        Preconditions.checkNotNull(id, "id cannot be null");
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void start() {
        Preconditions.checkState(state.compareAndSet(State.LATENT, State.STARTED), "Cannot be started more than once");
        Preconditions.checkState(!executorService.isShutdown(), "Already started");
        Preconditions.checkState(!hasLeadership, "Already has leadership");
        client.getConnectionStateListenable().addListener(listener);
        requeue();
    }

    private boolean requeue() {
        Preconditions.checkState(state.get() == State.STARTED, "close() has already been called");
        return internalRequeue();
    }

    private synchronized boolean internalRequeue() {
        if(!isQueued && (state.get() == State.STARTED)) {
            isQueued = true;
            Future<Void> task = executorService.submit(() -> {
                try {
                    doWorkLoop();
                } finally {
                    clearIsQueued();
                    if(autoRequeue.get()) {
                        internalRequeue();
                    }
                }
                return null;
            });
            ourTask.set(task);
            return true;
        } else {
            return false;
        }
    }

    public Collection<Participant> getParticipants() throws Exception {
        Collection<String> participantNodes = mutex.getParticipantNodes();
        return getParticipants(client, participantNodes);
    }

    static Collection<Participant> getParticipants(CuratorFramework client, Collection<String> particpaintNodes) throws  Exception {
        ImmutableList.Builder<Participant> builder = ImmutableList.builder();
        boolean isLeader = true;
        for (String path : particpaintNodes) {
            Participant  participant = participantForPath(client, path, isLeader);
            if(participant != null) {
                builder.add(participant);
                isLeader = false;
            }
        }
        return builder.build();
    }

    public Participant getLeader() throws Exception {
        Collection<String> participantNodes = mutex.getParticipantNodes();
        return getLeader(client, participantNodes);
    }

    static Participant getLeader(CuratorFramework client, Collection<String> participantNodes) throws Exception {
        Participant result = null;
        for (String path : participantNodes) {
            result = participantForPath(client, path, true);
            if(result != null) {
                return result;
            }
        }
        return new Participant();
    }

    public boolean hasLeadership() {
        return hasLeadership;
    }

    public synchronized void interruptLeadership() {
        Future<?> task = ourTask.get();
        if(task != null) {
            task.cancel(true);
        }
    }

    private static Participant participantForPath(CuratorFramework client, String path, boolean markAsLeader) throws Exception {
        try {
            byte[] bytes = client.getData().forPath(path);
            String thisId = new String(bytes, StandardCharsets.UTF_8);
            return new Participant(thisId, markAsLeader);
        } catch(KeeperException.NoNodeException e) {
            return null;
        }
    }

    void doWork() throws Exception {
        hasLeadership = false;
        try {
            mutex.acquire();
            hasLeadership = true;
            try {

            }
        }
    }

    private static class WrappedListener implements LeaderSelectorListener {
        private final LeaderSelector leaderSelector;
        private final LeaderSelectorListener listener;

        public WrappedListener(LeaderSelector selector, LeaderSelectorListener listener) {
            this.leaderSelector = selector;
            this.listener = listener;
        }

        @Override
        public void takeLeadership(CuratorFramework client) throws Exception {
            listener.takeLeadership(client);
        }

        @Override
        public void stateChanged(CuratorFramework client, ConnectionState newState) {
            try {
                listener.stateChanged(client, newState);
            } catch(CancelLeadershipException dummy) {
                leaderSelector.interruptLeadership();
            }
        }
    }*/
}
