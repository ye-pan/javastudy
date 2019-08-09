package com.yp.jvm;

public class JVMStackOOM {
    private void dontStop() {
        while(true) {
        }
    }

    public void stackLeakByThread() {
        while(true) {
            new Thread(this::dontStop).start();
        }
    }

    public static void main(String[] args) {
        JVMStackOOM oom = new JVMStackOOM();
        oom.stackLeakByThread();
    }
}
