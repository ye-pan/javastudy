package com.yp.refactor.before;

public class SeparateQueryFromModifier {

    public void checkSecurity(String[] people) {
        String found = foundMiscreant(people);
        someLaterCode(found);
    }

    private void someLaterCode(String found) {

    }

    public String foundMiscreant(String[] people) {
        for (String person : people) {
            if("Don".equals(person)) {
                sendAlert();
                return person;
            }
            if("John".equals(person)) {
                sendAlert();
                return person;
            }
        }
        return "";
    }

    private void sendAlert() {

    }
}
