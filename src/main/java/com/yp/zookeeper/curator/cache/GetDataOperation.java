package com.yp.zookeeper.curator.cache;

import org.apache.curator.utils.PathUtils;

public class GetDataOperation implements Operation {
    private final PathChildrenCache cache;

    private final String fullPath;

    public GetDataOperation(PathChildrenCache cache, String fullPath) {
        this.cache = cache;
        this.fullPath = PathUtils.validatePath(fullPath);
    }

    @Override
    public void invoke() throws Exception {
        cache.getDataAndStat(fullPath);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || getClass() != obj.getClass()) {
            return false;
        }

        if(this == obj) {
            return true;
        }

        GetDataOperation that = (GetDataOperation) obj;
        if(!fullPath.equals(that.fullPath)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return fullPath.hashCode();
    }

    @Override
    public String toString() {
        return "GetDataOperation{fullPath=" + fullPath + "}";
    }
}
