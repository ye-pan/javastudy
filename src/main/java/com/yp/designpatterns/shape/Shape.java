package com.yp.designpatterns.shape;

import com.yp.designpatterns.Point;

public interface Shape {

    void boundingBox(Point bottomLeft, Point topRight);

    Manipulator createManipulator();
}
