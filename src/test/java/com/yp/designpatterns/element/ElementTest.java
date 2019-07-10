package com.yp.designpatterns.element;

import org.junit.Test;

import static org.junit.Assert.*;

public class ElementTest {

    @Test
    public void testVisitorCompositeElement() {
        Visitor visitor = new Visitor();
        CompositeElement compositeElement = new CompositeElement();
        compositeElement.addElement(new ElementA());
        compositeElement.addElement(new ElementB());
        compositeElement.accept(visitor);
    }
}