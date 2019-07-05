package com.yp.designpatterns.help;

public class Button extends Widget {
    public Button(HelpHandler handler) {
        super(handler);
    }

    public Button(HelpHandler helpHandler, Topic topic) {
        super(helpHandler, topic);
    }

    @Override
    public void handleHelp() {
        if(hasHelp()) {
            System.out.println("help button");
        } else {
            super.handleHelp();
        }
    }
}
