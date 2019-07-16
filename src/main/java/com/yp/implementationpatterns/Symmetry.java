package com.yp.implementationpatterns;

/**
 * 原则——对称性
 */
public class Symmetry {

    private int count;

    public void process() {
        input();
        count++;
        output();
    }

    public void processV2() {
        input();
        incrementCount();
        output();
    }

    public void processV3() {
        input();
        tally();
        output();
    }

    private void tally() {

    }

    private void incrementCount() {
        count++;
    }

    private void output() {

    }

    private void input() {

    }
}
