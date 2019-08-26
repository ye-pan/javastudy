package com.yp.zookeeper.curator.cache;

public class EventOperation implements Operation {
    private final PathChildrenCache cache;
    private final PathChildrenCacheEvent event;

    public
    EventOperation(PathChildrenCache cache, PathChildrenCacheEvent event) {
        this.cache = cache;
        this.event = event;
    }
    @Override
    public void invoke() throws Exception {
        cache.callListeners(event);
    }
}
