package com.yp.algorithm;

public class Ranges {
    public static int binarySearch(int[] arr, int key) {
        int low = 0;
        int hi = arr.length - 1;
        while(low <= hi) {
            int mid = (hi + low) / 2;
            if(key > arr[mid]) {
                low = mid + 1;
            } else if(key < arr[mid]) {
                hi = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static long sum(int[] range) {
        long sum = 0;
        for (int num : range) {
            sum += num;
        }
        return sum;
    }

    public static long mergeSum(int[] range) {
        return mergeSum(range, 0, range.length - 1);
    }

    private static long mergeSum(int[] range, int begin, int end) {
        if((end - begin + 1) <= 10000) {
            long sum = 0;
            for(int i = begin; i <= end; i++) {
                sum += range[i];
            }
            return sum;
        } else {
            int mid = (end + begin) / 2;
            long left = mergeSum(range, begin, mid);
            long right = mergeSum(range, mid + 1, end);
            return left + right;
        }
    }

    public static void mergeReverse(int[] range) {
        mergeReverse(range, 0, range.length - 1);
    }

    private static void mergeReverse(int[] range, int begin, int end) {
        if(begin < end) {
            swap(range, begin, end);
            mergeReverse(range, begin + 1, end - 1);
        }
    }

    public static void reverse(int[] range) {
        int begin = 0, end = range.length - 1;
        while(begin < end) {
            swap(range, begin++, end--);
        }
    }

    private static void swap(int[] range, int i, int j) {
        int tmp = range[i];
        range[i] = range[j];
        range[j] = tmp;
    }
}
