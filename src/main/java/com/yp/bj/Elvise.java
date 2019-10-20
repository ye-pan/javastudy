package com.yp.bj;

public class Elvise {
    public static final Elvise ELVIS = new Elvise();

    private Elvise() {

    }

    private static final Boolean V = Boolean.TRUE;
    private final Boolean elvise = V;

    public Boolean isElvise() {
        return elvise;
    }

    public static void main(String[] args) {
        System.out.println(ELVIS);
        System.out.println(ELVIS.elvise);
        System.out.println(ELVIS.isElvise() ? "1" : "2");
    }
}
