package com.yp.designpatterns.help;

public class Dialog extends Widget {
    public Dialog(HelpHandler handler) {
        super(handler);
    }

    public Dialog(HelpHandler helpHandler, Topic topic) {
        super(helpHandler, topic);
    }

    @Override
    public void handleHelp() {
        if(hasHelp()) {
            System.out.println("help dialog");
        } else {
            super.handleHelp();
        }
    }
}
