package com.yp.designpatterns.element;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class CompositeElement implements Element {

    private List<Element> list;

    public CompositeElement() {
        list = new LinkedList<>();
    }

    @Override
    public void accept(Visitor visitor) {
        Iterator<Element> it = list.iterator();
        while(it.hasNext()) {
            it.next().accept(visitor);
        }
        visitor.visitCompositeElement(this);
    }

    public void addElement(Element e) {
        list.add(e);
    }
}
