package com.yp.designpatterns.glyph;

import com.yp.designpatterns.window.Window;


public class Glyph {

    protected Glyph() {

    }

    public void draw(Window window, GlyphContext gc) {

    }

    public void setFont(Font font, GlyphContext gc) {

    }

    public Font getFont(GlyphContext gc) {
        return null;
    }

    public void first(GlyphContext gc) {

    }

    public void next(GlyphContext gc) {

    }

    public boolean isDone(GlyphContext gc) {
        return true;
    }

    public Glyph current(GlyphContext gc) {
        return null;
    }

    public void insert(Glyph glyph, GlyphContext gc) {

    }

    public void remove(GlyphContext gc) {

    }
}
