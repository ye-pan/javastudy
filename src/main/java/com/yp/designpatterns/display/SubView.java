package com.yp.designpatterns.display;

public class SubView extends View {

    @Override
    protected void doDisplay() {
        System.out.println("SubDisplay");
    }
}
