package com.yp.designpatterns.shape;

import com.yp.designpatterns.shape.Shape;
import com.yp.designpatterns.shape.TextShape;
import com.yp.designpatterns.shape.TextShapeHolder;
import com.yp.designpatterns.shape.TextView;
import org.junit.Test;
import static org.junit.Assert.*;
public class ShapeTest {


    @Test
    public void testShapeAdapter() {
        Shape shape = new TextShape();
        assertNotNull(shape);

        TextView textView = new TextView();
        shape = new TextShapeHolder(textView);
        assertNotNull(shape);
    }
}
