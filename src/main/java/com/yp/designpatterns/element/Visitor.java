package com.yp.designpatterns.element;

public class Visitor {
    public void visitElementA(ElementA a) {
        System.out.println(a);
    }

    public void visitElementB(ElementB b) {
        System.out.println(b);
    }

    public void visitCompositeElement(CompositeElement compositeElement) {
        System.out.println(compositeElement);
    }
}
