package com.yp.designpatterns.command;

public class Document {
    private String name;
    public Document(String name) {
        this.name = name;
    }

    public void open() {
        System.out.println("open document");
    }

    public void past() {
        System.out.println("document paste");
    }
}
