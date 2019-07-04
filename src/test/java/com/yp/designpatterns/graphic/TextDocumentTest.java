package com.yp.designpatterns.graphic;

import org.junit.Test;

import static org.junit.Assert.*;

public class TextDocumentTest {

    @Test
    public void testProxyImage() {
        TextDocument document = new TextDocument();
        document.insert(new ImageProxy("fileName"));
    }

}