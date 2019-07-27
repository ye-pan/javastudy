package com.yp.refactor.after;

public class SplitTemporaryVariable {
    private double primaryForce;

    private double mass;

    private double secondaryForce;

    private int delay;

    public double getDistanceTravelled(int time) {
        double result;
        final double primaryAcc = primaryForce / mass;
        int primaryTime = Math.min(time, delay);
        result = 0.5 * primaryAcc * primaryTime * primaryTime;
        int secondaryTime = time - delay;
        if(secondaryTime > 0) {
            double primaryVel = primaryAcc * delay;
            final double secondaryAcc = (primaryForce + secondaryForce) / mass;
            result += primaryVel * secondaryTime + 0.5 * secondaryAcc * secondaryTime * secondaryTime;
        }
        return result;
    }


    public double getDistanceTravelledByExtractMethod(int time) {
        if(hasSecondaryTime(time)) {
            return (primaryResult(time) + secondaryResult(time));
        } else {
            return primaryResult(time);
        }
    }

    private double secondaryResult(int time) {
        return primaryVel() * secondaryTime(time) + 0.5 * secondaryAcc() * Math.pow(secondaryTime(time), 2);
    }

    private double primaryVel() {
        return primaryAcc() * delay;
    }

    private boolean hasSecondaryTime(int time) {
        return secondaryTime(time) > 0;
    }

    private int secondaryTime(int time) {
        return time - delay;
    }

    private double primaryResult(int time) {
        int primaryTime = Math.min(time, delay);
        return 0.5 * primaryAcc() * Math.pow(primaryTime, 2);
    }

    private double secondaryAcc() {
        return (primaryForce + secondaryForce) / mass;
    }

    private double primaryAcc() {
        return primaryForce / mass;
    }
}
