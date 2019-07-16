package com.yp.algorithm;

import com.google.common.base.Preconditions;

public class Sorts {
    private static final StdDraw DRAW = new StdDraw();
    private static boolean IS_DRAW = true;
    private static final int SLEEP = 2000;

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
            draw(arr);
        }
    }

    /**
     * 插入排序
     *
     * @param arr
     * @param <T>
     */
    public static <T extends Comparable<T>> void insertion(T[] arr) {
        Preconditions.checkNotNull(arr);
        Preconditions.checkArgument(arr.length > 0);
        //TODO-yepan 还可以优化提高速度，需要在内循环中将较大的元素都向右移动而不总是交换两个元素
        for(int i = 1; i < arr.length; i++) {
            /*T v = arr[i];
            int j;
            for(j = i - 1; j >= 0 && less(v, arr[j]); j--) {
                swap(arr, j, j + 1);
            }
            arr[j + 1] = v;*/
            for(int j = i; j > 0 && less(arr[j], arr[j - 1]); j--) {
                swap(arr, j, j-1);
            }
            draw(arr);
        }
    }

    public static <T> void draw(T[] arr) {
        if(!IS_DRAW || !(arr instanceof  Number[])) {
            return;
        }
        DRAW.clear();
        for(int i = 0; i < arr.length; i++) {
            T v = arr[i];
            Number n = (Number)v;
            double x = 1.0 * i / arr.length;
            double y = n.doubleValue() / 2;
            double rw = 0.5 / arr.length;
            double rh = n.doubleValue() / 2;
            DRAW.filledRectangle(x, y, rw, rh);
        }
        DRAW.pause(SLEEP);
    }
}
