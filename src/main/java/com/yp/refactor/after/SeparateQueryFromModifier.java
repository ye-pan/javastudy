package com.yp.refactor.after;

public class SeparateQueryFromModifier {

    public void checkSecurity(String[] people) {
        sendAlert(people);
        String found = foundPerson(people);
        someLaterCode(found);
    }

    private void someLaterCode(String found) {

    }

    public void sendAlert(String[] people) {
        if(!"".equals(foundPerson(people))) {
            sendAlert();
        }
    }

    private void sendAlert() {

    }

    private String foundPerson(String[] people) {
        for (String person : people) {
            if("Don".equals(person)) {
                return person;
            }
            if("John".equals(person)) {
                return person;
            }
        }
        return "";
    }
}
