package com.yp.designpatterns.window;

import com.yp.designpatterns.Coord;
import com.yp.designpatterns.Point;

public interface WindowImpl {

    void impTop();

    void impBottom();

    void impSetExtent(Point extent);

    void impSetOrigin(Point at);

    void deviceRect(Coord coord1, Coord coord2, Coord coord3, Coord coord4);

    void deviceText(String text, Coord coord, Coord coord2);

    void deviceBitmap(String text, Coord coord, Coord coord2);
}
