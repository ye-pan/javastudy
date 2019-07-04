package com.yp.designpatterns.compiler;

import java.io.OutputStream;

public class RISCCodeGenerator extends CodeGenerator {
    private BytecodeStream output;
    public RISCCodeGenerator(BytecodeStream output) {
        this.output = output;
    }
}
