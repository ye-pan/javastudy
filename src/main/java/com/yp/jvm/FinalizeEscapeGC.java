package com.yp.jvm;

/**
 * 展示了Java中可达性分析再GC过程中如何执行的，以及Object#finalize方法的执行
 */
public class FinalizeEscapeGC {
    public static FinalizeEscapeGC SAVE_HOOK;

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method executed!");
        SAVE_HOOK = this;
    }

    public static void main(String[] args) throws Exception {
        SAVE_HOOK = new FinalizeEscapeGC();
        SAVE_HOOK = null;
        System.gc();//触发GC
        Thread.sleep(500);
        isAlive(SAVE_HOOK);

        SAVE_HOOK = null;//再次清空引用为GC做准备
        System.gc();
        Thread.sleep(500);
        isAlive(SAVE_HOOK);
    }

    private static void isAlive(FinalizeEscapeGC obj) {
        if(obj != null) {
            System.out.println("yes, i am still alive");
        } else {
            System.out.println("no, iam dead");
        }
    }
}
