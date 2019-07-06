package com.yp.designpatterns.exp;

import java.util.HashMap;
import java.util.Map;

public class Context {

    private Map<String, Boolean> context;

    public Context() {
        context = new HashMap<>();
    }

    public void assign(VariableExp exp, boolean a) {
        context.put(exp.toString(), a);
    }

    public boolean lookup(VariableExp x) {
        return lookup(x.toString());
    }

    private boolean lookup(String name) {
        return context.get(name);
    }
}
