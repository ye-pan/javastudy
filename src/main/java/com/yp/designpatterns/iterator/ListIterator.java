package com.yp.designpatterns.iterator;

import java.util.List;

public class ListIterator<T> {
    private List<T> list;
    private int current;

    public ListIterator(List<T> list) {
        this.list = list;
        this.current = 0;
    }

    public void first() {
        current = 0;
    }

    public void next() {
        current++;
    }

    public boolean isDone() {
        return current != list.size();
    }

    public T current() {
        return list.get(current);
    }
}
