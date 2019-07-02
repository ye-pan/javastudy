package com.yp.designpatterns.window;

import com.yp.designpatterns.Coord;
import com.yp.designpatterns.Point;

public class XWindowImpl implements WindowImpl {

    private Display display;

    private Drawable drawable;

    private GC gc;

    @Override
    public void impTop() {

    }

    @Override
    public void impBottom() {

    }

    @Override
    public void impSetExtent(Point extent) {

    }

    @Override
    public void impSetOrigin(Point at) {

    }

    @Override
    public void deviceRect(Coord x0, Coord y0, Coord x1, Coord y1) {
        double x = round(Coord.min(x0, x1));
        double y = round(Coord.min(y0, y1));
        double w = round(abs(x0.mins(x1)));
        double h = round(abs(y0.mins(y1)));
        drawRetangle(display, drawable, gc, x, y, w, h);
    }

    private void drawRetangle(Display display, Drawable drawable, GC gc, double x, double y, double w, double h) {

    }

    private Coord abs(Coord mins) {
        return new Coord(Math.abs(mins.value()));
    }

    private double round(Coord min) {
        return min.value();
    }

    @Override
    public void deviceText(String text, Coord coord, Coord coord2) {

    }

    @Override
    public void deviceBitmap(String text, Coord coord, Coord coord2) {

    }
}
