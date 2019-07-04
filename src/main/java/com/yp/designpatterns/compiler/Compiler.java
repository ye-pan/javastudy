package com.yp.designpatterns.compiler;

import java.io.InputStream;

public class Compiler {
    public Compiler() {

    }

    public void compile(InputStream inputStream, BytecodeStream output) {
        Scanner scanner = new Scanner(inputStream);
        ProgramNodeBuilder builder = new ProgramNodeBuilder();
        Parser parser = new Parser();

        parser.parse(scanner, builder);
        RISCCodeGenerator generator = new RISCCodeGenerator(output);
        ProgramNode node = builder.getRootNode();
        node.traverse(generator);
    }
}
