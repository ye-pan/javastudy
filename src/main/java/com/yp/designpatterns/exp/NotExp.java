package com.yp.designpatterns.exp;

public class NotExp implements BooleanExp {
    private BooleanExp operate;

    public NotExp(BooleanExp operate) {
        this.operate = operate;
    }

    @Override
    public boolean evaluate(Context ctx) {
        return !operate.evaluate(ctx);
    }

    @Override
    public BooleanExp replace(BooleanExp source, BooleanExp target) {
        return operate.replace(source, target);
    }

    @Override
    public BooleanExp copy() {
        return new NotExp(operate.copy());
    }

    @Override
    public String toString() {
        return "(!" + operate + ")";
    }
}
