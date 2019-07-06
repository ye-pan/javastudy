package com.yp.designpatterns.iterator;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ListIteratorTest {

    @Test
    public void testNumberList() {
        List<Integer> numberList = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            numberList.add(i);
        }
        ListIterator<Integer> iterator = new ListIterator<>(numberList);
        while(iterator.isDone()) {
            System.out.print(iterator.current());
            iterator.next();
        }
    }
}