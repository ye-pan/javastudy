package com.yp.algorithm;

import com.google.common.base.Preconditions;

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



    private static <T extends Comparable<T>> boolean less(T a, T b) {
        return a.compareTo(b) < 0;
    }

    private static <T> void swap(T[] arr, int i, int j) {
        T tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**
     * 选择排序
     *
     * @param arr
     * @param <T>
     */
    public static <T extends Comparable<T>> void selection(T[] arr) {
        Preconditions.checkNotNull(arr);
        Preconditions.checkArgument(arr.length > 0);

        for(int i = 0; i < arr.length; i++) {
            int min = i;//最小元素位置
            for(int j = i + 1; j < arr.length; j++) {
                if(less(arr[j], arr[min])) {
                    min = j;
                }
            }

            if(min != i) {
                swap(arr, i, min);
            }
        }
    }
}
