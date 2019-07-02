package com.yp.designpatterns.shape;

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
}
