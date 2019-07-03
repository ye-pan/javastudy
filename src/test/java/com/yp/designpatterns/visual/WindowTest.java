package com.yp.designpatterns.visual;

import org.junit.Test;

import static org.junit.Assert.*;

public class WindowTest {

    /**
     * Decorator模式，展现了对象层次的包装。
     */
    @Test
    public void testDecoratorDraw() {
        Window window = new Window();
        //普通
        TextView textView = new TextView();
        window.setContents(textView);

        //带边框
        BorderDecorator decorator = new BorderDecorator(textView, 2);
        window.setContents(decorator);
    }

}