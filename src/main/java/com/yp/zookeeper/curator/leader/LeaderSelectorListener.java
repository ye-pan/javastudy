package com.yp.zookeeper.curator.leader;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.state.ConnectionStateListener;

public interface LeaderSelectorListener extends ConnectionStateListener {
    void takeLeadership(CuratorFramework client) throws  Exception;
}
