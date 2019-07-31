package com.yp.refactor.before;

public class ConsolidateConditionalExpression {
    private int seniority;
    private int monthsDisabled;
    private boolean isPartTime;

    public double disabilityAmount() {
        if(seniority < 2)
            return 0;

        if(monthsDisabled > 12)
            return 0;

        if(isPartTime)
            return 0;

        //...... compute result
        return -1;
    }
}
