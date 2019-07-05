package com.yp.designpatterns.command;

public class OpenCommand implements Command {

    private Application application;

    private String response;

    public OpenCommand(Application application) {
        this.application = application;
    }

    @Override
    public void execute() {
        String name = askName();
        if(name != null) {
            Document doc = new Document(name);
            application.add(doc);
            doc.open();
        }
    }

    public String askName() {
        return "";
    }
}
