package com.yp.bj;

import java.util.HashSet;
import java.util.Set;

/**
 * 此处需要注意，i-1是默认是Integer，而泛型使用的是Short，所以即使值相等但类型不同，实际remove没有删除任何东西
 */
public class ShortSet {

    public static void main(String[] args) {
        Set<Short> shorts = new HashSet<>();
        for (Short i = 0; i < 100; i++) {
            shorts.add(i);
            shorts.remove(i - 1);
        }
        System.out.println(shorts.size());
        System.out.println(Short.valueOf((short)1).equals(1));
    }
}
