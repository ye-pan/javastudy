package com.yp.zookeeper.curator.locks;

import com.yp.zookeeper.curator.ZKSimpleTest;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class InterProcessMultiLockTest extends ZKSimpleTest {

    @Test
    public void testSomeReleasesFail() throws IOException {
        InterProcessLock goodLock = new InterProcessMutex(client, "/our-lock-1");
        final InterProcessLock otherGoodLock = new InterProcessMutex(client, "/our-lock-2");
        InterProcessLock badLock = new InterProcessLock() {
            @Override
            public void acquire() throws Exception {
                otherGoodLock.acquire();
            }

            @Override
            public boolean acquire(long time, TimeUnit unit) throws Exception {
                return otherGoodLock.acquire(time, unit);
            }

            @Override
            public void release() throws Exception {
                throw new Exception("foo");
            }

            @Override
            public boolean isAcquiredInThisProcess() {
                return otherGoodLock.isAcquiredInThisProcess();
            }
        };

        InterProcessMultiLock lock = new InterProcessMultiLock(List.of(goodLock, badLock));
        try {
            lock.acquire();
            lock.release();
            fail();
        } catch(Exception ignore) {
            ignore.printStackTrace();
        }

        assertFalse(goodLock.isAcquiredInThisProcess());
        assertTrue(otherGoodLock.isAcquiredInThisProcess());
    }

}