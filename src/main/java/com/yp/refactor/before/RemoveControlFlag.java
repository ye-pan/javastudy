package com.yp.refactor.before;

public class RemoveControlFlag {

    public void checkSecurity(String[] people) {
        boolean found = false;
        for (String person : people) {
            if(!found) {
                if("Don".equals(person)) {
                    sendAlert();
                    found = true;
                }
                if("John".equals(person)) {
                    sendAlert();
                    found = true;
                }
            }
        }
    }

    private void sendAlert() {

    }
}
