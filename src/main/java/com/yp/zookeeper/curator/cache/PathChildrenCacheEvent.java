package com.yp.zookeeper.curator.cache;

import java.util.List;

public class PathChildrenCacheEvent {
    public enum Type {
        CHILD_ADDED, CHILD_UPDATED, CHILD_REMOVED,
        CONNECTION_SUSPENDED, CONNECTION_RECONNECTED,
        CONNECTION_LOST, INITIALIZED
    }

    private  final Type type;
    private final ChildData data;

    PathChildrenCacheEvent(Type type, ChildData data) {
        this.type = type;
        this.data = data;
    }

    public Type getType() {
        return type;
    }

    public ChildData getData() {
        return data;
    }

    public List<ChildData> getInitialData() {
        return null;
    }

    @Override
    public String toString() {
        return "PathChildrenCacheEvent{" + "type=" + type + ", data=" + data + "}";
    }
}
