package com.yp.refactor.after;

import java.util.HashMap;
import java.util.Map;

public class CustomerObject {
    private final static Map<String, CustomerObject> INSTANCE = new HashMap<>();

    private final String name;

    private CustomerObject(String name) {
        this.name = name;
    }

    public static CustomerObject newInstance(String name) {
        return INSTANCE.computeIfAbsent(name, k->new CustomerObject(k));
    }

    public String getName() {
        return name;
    }

    public void store() {
        INSTANCE.put(name, this);
    }
}
