package com.yp.jvm;

import java.util.Random;

/**
 * VM Args: -Xss128k
 */
public class JVMStackSOF {
    private int stackLength = 1;
    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) {
        JVMStackSOF oom = new JVMStackSOF();
        try {
            oom.stackLeak();
        } catch(Throwable e) {
            System.out.println("Stack length:" + oom.stackLength);
            throw e;
        }
    }
}
