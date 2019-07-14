package com.yp.algorithm;

import com.yp.util.IntUtils;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;
public class SortsTest {

    @Test
    public void testInsertSort() {
        int[] arr = IntUtils.randome(100);
        Sorts.insertSort(arr);
        for(int i = 1; i < arr.length; i++) {
            assertTrue("升序排序", arr[i-1] <= arr[i]);
        }
    }

    @Test
    public void testSelectionSort() {
        Integer[] arr = IntUtils.randomeWrapper(10);
        System.out.println(Arrays.toString(arr));
        Sorts.selection(arr);
        System.out.println(Arrays.toString(arr));
        for(int i = 1; i < arr.length; i++) {
            assertTrue("升序", arr[i - 1] <= arr[i]);
        }
    }
}
