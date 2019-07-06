package com.yp.designpatterns.exp;

public class OrExp implements BooleanExp {
    private BooleanExp operate1;
    private BooleanExp operate2;

    public OrExp(BooleanExp operate1, BooleanExp operate2) {
        this.operate1 = operate1;
        this.operate2 = operate2;
    }

    @Override
    public boolean evaluate(Context ctx) {
        return operate1.evaluate(ctx) || operate2.evaluate(ctx);
    }

    @Override
    public BooleanExp replace(BooleanExp source, BooleanExp target) {
        return new OrExp(operate1.replace(source, target), operate2.replace(source, target));
    }

    @Override
    public BooleanExp copy() {
        return new OrExp(operate1.copy(), operate2.copy());
    }

    @Override
    public String toString() {
        return "(" + operate1 + " || " + operate2 + ")";
    }
}
