package com.yp.designpatterns.graphic;

import com.yp.designpatterns.Point;

import java.io.InputStream;
import java.io.OutputStream;

public class ImageProxy implements Graphic {
    private String fileName;
    private Point extent;
    private Image image;

    public ImageProxy(String fileName) {
        this.fileName = fileName;
        extent = Point.ZERO;
    }

    @Override
    public void draw(Point point) {
        getImage().draw(point);
    }

    @Override
    public void handleMouse(Event event) {
        getImage().handleMouse(event);
    }

    @Override
    public Point getExtent() {
        if(extent == Point.ZERO) {
            extent = getImage().getExtent();
        }
        return extent;
    }

    @Override
    public void load(InputStream in) {

    }

    @Override
    public void save(OutputStream out) {

    }

    protected Image getImage() {
        if(image == null) {
            image = new Image(fileName);
        }
        return image;
    }
}
