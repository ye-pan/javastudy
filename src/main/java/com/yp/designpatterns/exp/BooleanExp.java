package com.yp.designpatterns.exp;

public interface BooleanExp {
    boolean evaluate(Context ctx);
    BooleanExp replace(BooleanExp source, BooleanExp target);
    BooleanExp copy();
}
