package com.yp.designpatterns.command;

public class PasteCommand implements Command {
    private Document document;
    public PasteCommand(Document document) {
        this.document = document;
    }
    @Override
    public void execute() {
        document.past();
    }
}
