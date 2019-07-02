package com.yp.designpatterns.shape;

public class Point {
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
}
