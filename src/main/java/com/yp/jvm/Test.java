package com.yp.jvm;

/**
 * 静态块可以给后面申明的类变量赋值，但无法使用在静态块中使用该变量
 */
public class Test {

    static {
        i = 2;
        //System.out.println(i);
    }

    static int i;

    public static void main(String[] args) {
        System.out.println(i);
    }
}
