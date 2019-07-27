package com.yp.refactor.before;

public class ReplaceMethodWithMethodObject {

    public int gamma(int inputVal, int quantity, int yearToDate) {
        int importantVal1 = (inputVal * quantity) + delta();
        int importantVal2 = (inputVal * yearToDate) + 100;
        if((yearToDate - importantVal1) > 100) {
            importantVal2 -= 20;
        }
        int importantVal3 = importantVal2 * 7;
        return importantVal3 - 2 * importantVal1;
    }

    int delta() {
        return 0;
    }
}
