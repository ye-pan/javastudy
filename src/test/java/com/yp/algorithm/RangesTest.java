package com.yp.algorithm;

import com.yp.util.NumberUtils;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class RangesTest {

    private int[] range;
    private long expected;

    @Before
    public void init() {
        int begin = 1, end = 1001;
        range = NumberUtils.range(begin, end);
        expected = (long)(begin + end) * (end - begin + 1) / 2;
    }
    @Test
    public void testSumArray() {
        long sum = Ranges.sum(range);
        assertEquals(expected, sum);
    }

    @Test
    public void testSumArray2() {
        long sum = Ranges.mergeSum(range);
        assertEquals(expected, sum);
    }

    @Test
    public void testBinarySearch() {
        int key = 555;
        int i = Ranges.binarySearch(range, key);
        assertTrue("找到了", i >= 0);
        key = -1;
        i = Ranges.binarySearch(range, key);
        assertTrue("没找到", i < 0);
    }

    @Test
    public void testReverse() {
        Ranges.reverse(range);
        Ranges.mergeReverse(range);
    }
}
