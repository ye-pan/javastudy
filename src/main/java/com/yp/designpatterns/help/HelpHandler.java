package com.yp.designpatterns.help;

public class HelpHandler {
    private HelpHandler handler;
    private Topic topic;

    public HelpHandler(HelpHandler handler) {
        this(handler, Topic.NO_HELP_TOPIC);
    }

    public HelpHandler(HelpHandler helpHandler, Topic topic) {
        this.handler = helpHandler;
        this.topic = topic;
    }

    public boolean hasHelp() {
        return topic != null && topic != Topic.NO_HELP_TOPIC;
    }

    public void handleHelp() {
        if(handler != null) {
            handler.handleHelp();
        }
    }
}
