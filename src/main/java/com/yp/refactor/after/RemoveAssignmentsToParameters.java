package com.yp.refactor.after;

public class RemoveAssignmentsToParameters {

    public int discount(final int inputVal, final int quantity, final int yearToDate) {
        int result = inputVal;
        if(inputVal > 50)
            return result -= 2;

        if(quantity > 100) {
            return result -= 1;
        }

        if(yearToDate > 10000) {
            return result -= 4;
        }
        return result;
    }
}
