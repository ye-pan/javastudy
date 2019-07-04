package com.yp.designpatterns.compiler;

import java.io.InputStream;

public class Scanner {
    private InputStream inputStream;

    public Scanner(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public Token scan() {
        return null;
    }
}
