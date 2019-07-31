package com.yp.refactor.after;

public class RemoveControlFlag {

    public void checkSecurity(String[] people) {
        for (String person : people) {
            if ("Don".equals(person)) {
                sendAlert();
                break;
            }
            if ("John".equals(person)) {
                sendAlert();
                break;
            }
        }
    }

    private void sendAlert() {

    }
}
