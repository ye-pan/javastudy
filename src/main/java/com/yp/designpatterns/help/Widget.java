package com.yp.designpatterns.help;

public class Widget extends HelpHandler {

    public Widget(HelpHandler handler) {
        super(handler);
    }

    public Widget(HelpHandler helpHandler, Topic topic) {
        super(helpHandler, topic);
    }
}
