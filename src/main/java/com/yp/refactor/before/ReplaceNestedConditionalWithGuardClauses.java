package com.yp.refactor.before;

public class ReplaceNestedConditionalWithGuardClauses {

    private static final double ADJ_FACTOR = 0.75;
    private boolean isDead;
    private boolean isSeparated;
    private boolean isRetired;
    private double capital;
    private double intRate;
    private double duration;
    private double income;

    public double getPayAmount() {
        double result;
        if(isDead) {
            result = deadAmount();
        } else {
            if(isSeparated) {
                result = separatedAmount();
            } else {
                if(isRetired) {
                    result = retiredAmount();
                } else {
                    result = normalPayAmount();
                }
            }
        }
        return result;
    }

    private double normalPayAmount() {
        return 0;
    }

    private double retiredAmount() {
        return 0;
    }

    private double separatedAmount() {
        return 0;
    }

    private double deadAmount() {
        return 0;
    }

    public double getAdjustedCapital() {
        double result = 0;
        if(capital > 0) {
            if(intRate > 0 && duration > 0) {
                result = (income / duration) * ADJ_FACTOR;
            }
        }
        return result;
    }
}
