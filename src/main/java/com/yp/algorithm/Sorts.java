package com.yp.algorithm;

public class Sorts {

    /**
     * 插入排序<br/>
     *
     *
     * @param arr
     */
    public static void insertSort(int[] arr) {
        for(int j  = 1; j < arr.length; j++) {
            int n = arr[j];
            int i = j - 1;
            while(i > 0 && arr[i] > n) {
                arr[i+1] = arr[i];
                i = i - 1;
            }
            arr[i + 1] = n;
        }
    }
}
