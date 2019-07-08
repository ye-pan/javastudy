package com.yp.designpatterns.composition;

import org.junit.Test;

import static org.junit.Assert.*;

public class CompositionTest {

    @Test
    public void testStrategy() {
        Composition quick = new Composition(new SimpleCompositor());
        Composition slick = new Composition(new TeXCompositor());
        Composition iconic = new Composition(new ArrayCompositor());
        quick.repair();
        slick.repair();
        iconic.repair();
    }
}