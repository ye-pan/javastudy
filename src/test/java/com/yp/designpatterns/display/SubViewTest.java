package com.yp.designpatterns.display;

import org.junit.Test;

import static org.junit.Assert.*;

public class SubViewTest {

    @Test
    public void testDisplay() {
        View view = new SubView();
        view.display();
    }
}