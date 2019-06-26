package com.yp.algorithm;

import java.util.*;

public class IPWhiteList {
    /**
     * TODO  hashset实现的白名单，内存占用太大，不能装下整个IP段
     */
    private Set<String> whiteList = new HashSet<>();
    private Map<Integer, Set<String>> hashError = new HashMap<>();
    public boolean addWhiteIpAddress(String ip) {
        synchronized (this) {
            return whiteList.add(ip);
        }
    }

    private int hash(String key) {
        int h = (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
        return h & 0X7FFFFFFF;
    }

    public boolean isWhiteIpAddress(String ip) {
        return whiteList.contains(ip);
    }

    public int size() {
        return whiteList.size();
    }

    public Map<Integer, Set<String>> hashError() {
        return hashError;
    }
}
