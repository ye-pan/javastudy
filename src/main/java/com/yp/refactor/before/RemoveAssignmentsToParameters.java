package com.yp.refactor.before;

public class RemoveAssignmentsToParameters {

    public int discount(int inputVal, int quantity, int yearToDate) {
        if(inputVal > 50)
            return inputVal -= 2;

        if(quantity > 100) {
            return inputVal -= 1;
        }

        if(yearToDate > 10000) {
            return inputVal -= 4;
        }
        return inputVal;
    }
}
