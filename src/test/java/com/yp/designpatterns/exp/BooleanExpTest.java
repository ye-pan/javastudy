package com.yp.designpatterns.exp;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 参照设计模式——interperter实现的一个Boolean表达式
 */
public class BooleanExpTest {

    @Test
    public void testBooleanExp() {
        Context ctx = new Context();
        VariableExp t = new VariableExp("true");
        ctx.assign(t, true);
        VariableExp f = new VariableExp("false");
        ctx.assign(f, false);


        VariableExp x = new VariableExp("x");
        VariableExp y = new VariableExp("y");
        ctx.assign(x, false);
        ctx.assign(y, true);

        BooleanExp expression = new OrExp(new AndExp(t, x), new AndExp(y, new NotExp(x)));
        assertTrue(expression.evaluate(ctx));
        System.out.println("x = " + ctx.lookup(x));
        System.out.println("y = " + ctx.lookup(y));
        System.out.println(expression);

        VariableExp z = new VariableExp("z");
        NotExp notZ = new NotExp(z);
        ctx.assign(z, true);

        expression = expression.replace(y, notZ);
        System.out.println("z = " + ctx.lookup(z));
        assertFalse(expression.evaluate(ctx));
        System.out.println(expression);
    }

}