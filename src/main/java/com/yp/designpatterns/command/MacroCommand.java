package com.yp.designpatterns.command;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class MacroCommand implements Command {
    private List<Command> commandList;

    public MacroCommand() {
        commandList = new LinkedList<>();
    }

    public void add(Command command) {
        commandList.add(command);
    }

    public void remove(Command command) {
        commandList.remove(command);
    }

    @Override
    public void execute() {
        Iterator<Command> it = commandList.iterator();
        while(it.hasNext()) {
            it.next().execute();
        }
    }
}
