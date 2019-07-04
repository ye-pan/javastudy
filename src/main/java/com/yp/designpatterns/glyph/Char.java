package com.yp.designpatterns.glyph;

import com.yp.designpatterns.window.Window;

public class Char extends Glyph {
    private char ch;

    public Char(char ch) {
        this.ch = ch;
    }

    @Override
    public void draw(Window window, GlyphContext gc) {
        System.out.print(ch);
    }
}
