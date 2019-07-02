package com.yp.designpatterns.shape;

public interface Shape {

    void boundingBox(Point bottomLeft, Point topRight);

    Manipulator createManipulator();
}
