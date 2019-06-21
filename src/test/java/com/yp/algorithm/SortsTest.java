package com.yp.algorithm;

import com.yp.util.IntUtils;
import org.junit.Test;
import static org.junit.Assert.*;
public class SortsTest {

    @Test
    public void testInsertSort() {
        int[] arr = IntUtils.randome(100);
        Sorts.insertSort(arr);
        for(int i = 2; i < arr.length; i++) {
            assertTrue("升序排序", arr[i-1] <= arr[i]);
        }
    }
}
