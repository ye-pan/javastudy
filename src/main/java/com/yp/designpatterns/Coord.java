package com.yp.designpatterns;

public class Coord {
    private double value;

    public Coord() {

    }

    public Coord(double val) {
        this.value = val;
    }

    public Coord add(Coord coord) {
        return new Coord(value + coord.value);
    }

    public double value() {
        return value;
    }

    public Coord mins(Coord x1) {
        return new Coord(value - x1.value);
    }

    public static Coord min(Coord a, Coord b) {
        return a.value() >= b.value() ? b : a;
    }

    public static Coord max(Coord a, Coord b) {
        return a.value() >= b.value() ? a : b;
    }
}
