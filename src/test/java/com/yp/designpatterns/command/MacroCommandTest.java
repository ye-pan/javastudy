package com.yp.designpatterns.command;

import org.junit.Test;

import static org.junit.Assert.*;

public class MacroCommandTest {

    @Test
    public void testExecute() {
        MacroCommand executor = new MacroCommand();
        executor.add(new OpenCommand(new Application()));
        executor.add(new PasteCommand(new Document("none")));
        executor.execute();
    }
}