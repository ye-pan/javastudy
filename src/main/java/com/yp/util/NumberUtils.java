package com.yp.util;

import com.google.common.base.Preconditions;
import java.util.Random;

public class NumberUtils {
    
    private NumberUtils() {
        throw new UnsupportedOperationException();
    }
    
    public static int[] ints(int len) {
        Preconditions.checkArgument(len > 0, "array length must gt 0");
        int[] ints = new int[len];
        Random randome = new Random();
        for (int i = 0; i < len; i++) {
            ints[i] = randome.nextInt(99999999);
        }
        return ints;
    }

    public static int[] range(int a, int b) {
        Preconditions.checkArgument(a < b);
        int[] ranges = new int[b - a + 1];
        for(int i = 0, j = a; i < ranges.length; i++, j++) {
            ranges[i] = j;
        }
        return ranges;
    }

    public static Integer[] integers(int len) {
        Preconditions.checkArgument(len > 0, "array length must gt 0");
        Integer[] ints = new Integer[len];
        Random randome = new Random();
        for (int i = 0; i < len; i++) {
            ints[i] = randome.nextInt(100);
        }
        return ints;
    }

    public static Double[] doubles(int len) {
        Preconditions.checkArgument(len > 0, "array length must gt 0");
        Double[] doubles = new Double[len];
        Random randome = new Random();
        for (int i = 0; i < len; i++) {
            doubles[i] = randome.nextDouble();
        }
        return doubles;
    }
}
