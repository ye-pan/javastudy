package com.yp.concurrent;

import org.junit.Test;

import java.util.List;
import java.util.Vector;

public class VectorConcurrentOpTest {

    @Test
    public void testOp() {
        Vector<Integer> vector = new Vector<>();
        for(int i = 0; i < 100; i++) {
            vector.add(i);
        }

        Runnable task1 = () -> {
            for(int i = 0; i < vector.size(); i++) {
                vector.remove(i);
            }
        };

        Runnable task2 = () -> {
            vector.forEach(System.out::print);
            System.out.println();
        };

        Runs.run(List.of(task1, task2));
    }
}
