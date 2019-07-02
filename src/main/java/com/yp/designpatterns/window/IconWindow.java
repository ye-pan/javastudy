package com.yp.designpatterns.window;

import com.yp.designpatterns.Coord;

public class IconWindow extends Window{

    private String bitmapName;

    public IconWindow(View view) {
        super(view);
    }

    @Override
    public void drawContents() {
        WindowImpl window = getWindow();
        if(window != null) {
            window.deviceBitmap(bitmapName, new Coord(0), new Coord(0));
        }
    }
}
