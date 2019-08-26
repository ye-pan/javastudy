package com.yp.zookeeper.curator.cache;

public class RefreshOperation implements Operation {
    private final PathChildrenCache cache;

    private final PathChildrenCache.RefreshMode mode;

    public RefreshOperation(PathChildrenCache cache, PathChildrenCache.RefreshMode mode) {
        this.cache = cache;
        this.mode = mode;
    }

    @Override
    public void invoke() throws Exception {
        cache.refresh(mode);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || getClass() != obj.getClass()) {
            return false;
        }

        if(this == obj) {
            return true;
        }

        RefreshOperation that = (RefreshOperation) obj;

        if(mode != that.mode) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return mode.hashCode();
    }

    @Override
    public String toString() {
        return "RefreshOperation(" + mode + "){}";
    }
}
