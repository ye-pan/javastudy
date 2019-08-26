package com.yp.zookeeper.curator.cache;

import org.apache.zookeeper.data.Stat;

import java.util.Arrays;
import java.util.Objects;

public class ChildData implements Comparable<ChildData> {
    private final String path;
    private final Stat stat;
    private final byte[] data;

    public ChildData(String path, Stat stat, byte[] data) {
        this.path = path;
        this.stat = stat;
        this.data = data;
    }

    @Override
    public int compareTo(ChildData o) {
        if(o == null || getClass() != o.getClass()) {
            return -1;
        }

        if(this == o) {
            return 0;
        }

        return path.compareTo(o.getPath());
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || getClass() != obj.getClass()) {
            return false;
        }

        if(this == obj) {
            return true;
        }

        ChildData childData = (ChildData) obj;

        if(!Objects.deepEquals(data, childData.getData())) {
            return false;
        }

        if(!Objects.equals(path, childData.getPath())) {
            return false;
        }

        return Objects.equals(stat, childData.getStat());
    }

    @Override
    public int hashCode() {
        int result = path != null ? path.hashCode() : 0;
        result = 31 * result + (stat != null ? stat.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(data);
        return result;
    }

    public String getPath() {
        return path;
    }

    public Stat getStat() {
        return stat;
    }

    public byte[] getData() {
        return data;
    }

    @Override
    public String toString() {
        return String.format("ChildData{path='%s', stat=%s, data=%s", path, stat.toString(), Arrays.toString(data));
    }
}
