package com.yp.refactor.after;

public class Gamma {
    private ReplaceMethodWithMethodObject obj;
    private int inputVal;
    private int quantity;
    private int yearToDate;
    private int importantVal1;
    private int importantVal2;
    private int importantVal3;

    Gamma(ReplaceMethodWithMethodObject obj, int inputVal, int quantity, int yeartToDate) {
        this.obj = obj;
        this.inputVal = inputVal;
        this.quantity = quantity;
        this.yearToDate = yeartToDate;
    }
    public int compute() {
        computeImportantVal1();
        computeImportantVal2();
        importantThing();
        computeImportantVal3();
        return computeImportantVal();
    }

    private int computeImportantVal() {
        return importantVal3 - 2 * importantVal1;
    }

    private void computeImportantVal3() {
        importantVal3 = importantVal2 * 7;
    }

    private void computeImportantVal2() {
        importantVal2 = (inputVal * yearToDate) + 100;
    }

    private void computeImportantVal1() {
        importantVal1 = (inputVal * quantity) + obj.delta();
    }

    private void importantThing() {
        if((yearToDate - importantVal1) > 100) {
            importantVal2 -= 20;
        }
    }
}
