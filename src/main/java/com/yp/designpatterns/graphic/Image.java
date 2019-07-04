package com.yp.designpatterns.graphic;

import com.yp.designpatterns.Point;

import java.io.InputStream;
import java.io.OutputStream;

public class Image implements Graphic {
    private String fileName;

    public Image(String fileName) {
        this.fileName = fileName;
    }
    @Override
    public void draw(Point point) {

    }

    @Override
    public void handleMouse(Event event) {

    }

    @Override
    public Point getExtent() {
        return null;
    }

    @Override
    public void load(InputStream in) {

    }

    @Override
    public void save(OutputStream out) {

    }
}
