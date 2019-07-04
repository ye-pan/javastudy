package com.yp.designpatterns.graphic;

import com.yp.designpatterns.Point;

import java.io.InputStream;
import java.io.OutputStream;

public interface Graphic {

    void draw(Point point);

    void handleMouse(Event event);

    Point getExtent();

    void load(InputStream in);

    void save(OutputStream out);
}
