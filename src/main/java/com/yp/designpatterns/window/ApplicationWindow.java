package com.yp.designpatterns.window;

public class ApplicationWindow extends Window {
    public ApplicationWindow(View view) {
        super(view);
    }

    @Override
    public void drawContents() {
        getView().drawOn(this);
    }
}
