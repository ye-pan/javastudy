package com.yp.util;

import com.google.common.base.Preconditions;
import java.util.Random;

public class IntUtils {
    
    private IntUtils() {
        throw new UnsupportedOperationException();
    }
    
    public static int[] randome(int len) {
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
}
