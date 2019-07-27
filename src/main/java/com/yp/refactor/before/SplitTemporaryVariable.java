package com.yp.refactor.before;

public class SplitTemporaryVariable {
    private double primaryForce;

    private double mass;

    private double secondaryForce;

    private int delay;

    public double getDistanceTravelled(int time) {
        double result;
        double acc = primaryForce / mass;
        int primaryTime = Math.min(time, delay);
        result = 0.5 * acc * primaryTime * primaryTime;
        int secondaryTime = time - delay;
        if(secondaryTime > 0) {
            double primaryVel = acc * delay;
            acc = (primaryForce + secondaryForce) / mass;
            result += primaryVel * secondaryTime + 0.5 * acc * secondaryTime * secondaryTime;
        }
        return result;
    }
}
