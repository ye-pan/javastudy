package com.yp.refactor.after;

public class ConsolidateConditionalExpression {
    private int seniority;
    private int monthsDisabled;
    private boolean isPartTime;

    public double disabilityAmount() {
        if(isNotEligibleForDisability()) {
            return 0;
        }
        //...... compute result
        return -1;
    }

    private boolean isNotEligibleForDisability() {
        return seniority < 2 || monthsDisabled > 12 || isPartTime;
    }
}
