package com.yp.designpatterns;

public class Point {
    public static final Point ZERO = new Point(Coord.zero(), Coord.zero());
    private double x;
    private double y;
    public Point(Coord bottom, Coord left) {
        this.x = bottom.value();
        this.y = left.value();
    }

    public double x() {
        return x;
    }

    public double y() {
        return y;
    }

    public void setCoords(Coord x, Coord y) {
        this.x = x.value();
        this.y = y.value();
    }
}
