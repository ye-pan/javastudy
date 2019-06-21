package com.yp.algorithm;

import com.yp.util.IntUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.StopWatch;
import static org.junit.Assert.*;

public class RangesTest {

    private int[] range;
    private long expected;
    private StopWatch time;

    @Before
    public void init() {
        int begin = 1, end = 1001;
        range = IntUtils.range(begin, end);
        expected = (long)(begin + end) * (end - begin + 1) / 2;
        time = new StopWatch("数组求和");
    }
    @Test
    public void testSumArray() {
        time.start("顺序求和");
        long sum = Ranges.sum(range);
        time.stop();
        assertEquals(expected, sum);
        System.out.println(time.prettyPrint());
    }

    @Test
    public void testSumArray2() {
        time.start("归并求和");
        long sum = Ranges.mergeSum(range);
        time.stop();
        assertEquals(expected, sum);
        System.out.println(time.prettyPrint());
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
