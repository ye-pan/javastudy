package com.yp.refactor.before;

public class ReplaceArrayWithObject {
    public String[] arrayObject() {
        String[] row = new String[3];
        row[0] = "Liverpool";
        row[1] = "15";
        row[2] = "test";
        return row;
    }
}
