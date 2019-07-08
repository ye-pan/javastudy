package com.yp.designpatterns.composition;

import com.yp.designpatterns.Coord;

public class Composition {
    private Compositior compositior;
    private int compoentCount;
    private int lineWidth;
    private int lineBreaks;
    private int lineCount;

    public Composition(Compositior compositior) {
        this.compositior = compositior;
    }

    public void repair() {
        Coord[] natural = new Coord[0];
        Coord[] stretchability = new Coord[0];
        Coord[] shrinkability = new Coord[0];
        int breakCount = compositior.compose(natural, stretchability, shrinkability, compoentCount, lineWidth, new int[] {lineBreaks, lineCount});
    }
}
