package com.yp.designpatterns.exp;

public class VariableExp implements BooleanExp {

    private String name;
    public VariableExp(String name) {
        this.name = name;
    }
    @Override
    public boolean evaluate(Context ctx) {
        return ctx.lookup(this);
    }

    @Override
    public BooleanExp replace(BooleanExp source, BooleanExp target) {
        if(equals(source)) {
            return target.copy();
        } else {
            return new VariableExp(this.name);
        }
    }

    @Override
    public BooleanExp copy() {
        return new VariableExp(name);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) {
            return false;
        }

        if(!(obj instanceof VariableExp)) {
            return false;
        }
        return ((VariableExp)obj).name.equals(name);
    }
}
