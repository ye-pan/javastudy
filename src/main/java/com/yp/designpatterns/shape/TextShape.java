package com.yp.designpatterns.shape;

import com.yp.designpatterns.Coord;
import com.yp.designpatterns.Point;

/**
 * 通过多继承实现adapter，Java中没有多继承只有继承adaptee，并实现target来实现。不推荐。
 */
public class TextShape extends TextView implements Shape {

    @Override
    public void boundingBox(Point bottomLeft, Point topRight) {
        Coord bottom = new Coord();
        Coord left = new Coord();
        Coord width = new Coord();
        Coord height = new Coord();

        getOrigin(bottom, left);
        getExtent(width, height);

        bottomLeft = new Point(bottom, left);
        topRight = new Point(bottom.add(height), left.add(width));
    }

    @Override
    public Manipulator createManipulator() {
        return new Manipulator(this);
    }
}
