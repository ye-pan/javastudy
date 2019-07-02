package com.yp.designpatterns.window;

import com.yp.designpatterns.Coord;
import com.yp.designpatterns.Point;

public class PMWindowImpl implements WindowImpl {
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
        Coord left = Coord.min(x0, y0);
        Coord right = Coord.max(x0, x1);
        Coord bottom = Coord.min(y0, y1);
        Coord top = Coord.max(y0, y1);
        Point[] point = new Point[4];
        point[0] = new Point(left, top);
        point[1] = new Point(right, top);
        point[2] = new Point(right, bottom);
        point[3] = new Point(left, bottom);
        
    }

    @Override
    public void deviceText(String text, Coord coord, Coord coord2) {

    }

    @Override
    public void deviceBitmap(String text, Coord coord, Coord coord2) {

    }
}
