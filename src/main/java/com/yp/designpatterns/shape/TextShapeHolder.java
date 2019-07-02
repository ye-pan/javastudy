package com.yp.designpatterns.shape;

/**
 * 通过复用adaptee来实现adapter，只实现了target接口。推荐。
 */
public class TextShapeHolder implements Shape {
    private TextView textView;

    public TextShapeHolder(TextView textView) {
        this.textView = textView;
    }
    @Override
    public void boundingBox(Point bottomLeft, Point topRight) {
        Coord bottom = new Coord();
        Coord left = new Coord();
        Coord width = new Coord();
        Coord height = new Coord();

        textView.getOrigin(bottom, left);
        textView.getExtent(width, height);

        bottomLeft = new Point(bottom, left);
        topRight = new Point(bottom.add(height), left.add(width));
    }

    @Override
    public Manipulator createManipulator() {
        return new Manipulator(textView);
    }

    public boolean isEmpty() {
        return textView.isEmpty();
    }
}
