package com.yp.algorithm;

import com.yp.util.NumberUtils;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;
public class SortsTest {

    @Test
    public void testSelectionSort() {
        Double[] arr = NumberUtils.doubles(10);
        System.out.println(Arrays.toString(arr));
        Sorts.selection(arr);
        System.out.println(Arrays.toString(arr));
        for(int i = 1; i < arr.length; i++) {
            assertTrue("升序", arr[i - 1] <= arr[i]);
        }
    }

    @Test
    public void testInsertionSort() throws Exception {
        Double[] arr = NumberUtils.doubles(10);
        System.out.println(Arrays.toString(arr));
        Sorts.insertion(arr);
        System.out.println(Arrays.toString(arr));
        for(int i = 1; i < arr.length; i++) {
            assertTrue("升序", arr[i - 1] <= arr[i]);
        }
    }
}
