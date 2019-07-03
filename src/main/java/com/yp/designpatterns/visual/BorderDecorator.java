package com.yp.designpatterns.visual;

public class BorderDecorator extends Decorator {
    private final int width;
    public BorderDecorator(VisualComponent component, int width) {
        super(component);
        this.width = width;
    }

    @Override
    public void draw() {
        super.draw();
        drawBorder(width);
    }

    private void drawBorder(int width) {

    }
}
