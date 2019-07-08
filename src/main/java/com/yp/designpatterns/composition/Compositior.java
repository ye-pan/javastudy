package com.yp.designpatterns.composition;

import com.yp.designpatterns.Coord;

public interface Compositior {
    int compose(Coord[] natural, Coord[] stretch, Coord[] shrink, int compoentCount, int lineWidth, int[] breaks);
}
