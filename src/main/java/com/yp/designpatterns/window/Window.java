package com.yp.designpatterns.window;

import com.yp.designpatterns.Point;

public class Window {
    private View view;
    private WindowImpl window;

    public Window(View view) {
        this.view = view;
    }

    public void drawContents() {

    }

    public void open() {

    }

    public void close() {

    }

    public void iconify() {

    }

    public void deiconify() {

    }

    public void setOrigin(Point at) {

    }

    public void setExtent(Point extent) {

    }

    public void raise() {

    }

    public void lower() {

    }

    public void drawLine(Point from, Point to) {

    }

    public void drawRect(Point start, Point end) {

    }

    public void drawPolygon(Point[] points) {

    }

    public void drawText(String text, Point point) {

    }

    protected View getView() {
        return view;
    }

    protected WindowImpl getWindow() {
        if(window == null) {
            window = WindowSystemFactory.getInstance().createWindowImpl();
        }
        return window;
    }
}
