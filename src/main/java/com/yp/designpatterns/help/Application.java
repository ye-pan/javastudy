package com.yp.designpatterns.help;

public class Application extends HelpHandler {
    public Application(HelpHandler handler) {
        super(handler);
    }

    public Application(HelpHandler helpHandler, Topic topic) {
        super(helpHandler, topic);
    }

    @Override
    public void handleHelp() {
        if(hasHelp()) {
            System.out.println("help application");
        } else {
            super.handleHelp();
        }
    }
}
