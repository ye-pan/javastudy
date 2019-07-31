package com.yp.refactor.after;

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
        if(isDead) {
            return deadAmount();
        }
        if(isSeparated) {
            return separatedAmount();
        }
        if(isRetired) {
            return retiredAmount();
        }
        return normalPayAmount();
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
        if(capital <= 0) {
            return result;
        }
        if(intRate <= 0  || duration <= 0) {
            return result;
        }
        return (income / duration) * ADJ_FACTOR;
    }
}
