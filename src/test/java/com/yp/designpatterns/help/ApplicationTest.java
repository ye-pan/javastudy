package com.yp.designpatterns.help;

import org.junit.Test;

import static org.junit.Assert.*;

public class ApplicationTest {

    @Test
    public void testChainOfResponsibility() {
        Application application = new Application(null, new Topic(3));
        Dialog dialog = new Dialog(application, new Topic(1));
        Button button = new Button(dialog, new Topic(2));

        button.handleHelp();
        dialog.handleHelp();
        application.handleHelp();
    }

}