package com.yp.designpatterns.compiler;

public class ProgramNodeBuilder {
    private ProgramNode node;

    public ProgramNode newVariable(String variableName) {
        return null;
    }

    public ProgramNode newAssignment(ProgramNode variable, ProgramNode expression) {
        return null;
    }

    public ProgramNode newRetrunStatement(ProgramNode value) {
        return null;
    }

    public ProgramNode newCondition(ProgramNode condition, ProgramNode turePart, ProgramNode falsePart) {
        return null;
    }

    public ProgramNode getRootNode() {
        return null;
    }
}
