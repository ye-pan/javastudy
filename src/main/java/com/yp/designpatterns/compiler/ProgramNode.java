package com.yp.designpatterns.compiler;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ProgramNode {

    private List<ProgramNode> nodeList;

    protected ProgramNode() {
        nodeList = new LinkedList<>();
    }

    public void getSourcePosition(int line, int index) {

    }

    public void add(ProgramNode node) {
        nodeList.add(node);
    }

    public void remove(ProgramNode node) {
        nodeList.remove(node);
    }

    public void traverse(CodeGenerator codeGenerator) {
        codeGenerator.visit(this);
        Iterator<ProgramNode> it = iterator();
        while(it.hasNext()) {
            it.next().traverse(codeGenerator);
        }
    }

    public Iterator<ProgramNode> iterator() {
        return nodeList.iterator();
    }
}
