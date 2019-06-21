package com.yp.algorithm;

public class LCS {
    public static String subString(String str1, String str2) {
        return subString(str1, str1.length() - 1, str2, str2.length() - 1);
    }

    private static String subString(String str1, int end1, String str2, int end2) {
        if(0 > end1 || 0 > end2) {
            return "";
        } else if(str1.charAt(end1) == str2.charAt(end2)){
            return subString(str1, end1 - 1, str2, end2 - 1) + str1.charAt(end1);
        } else {
            String sub1 = subString(str1, end1 - 1, str2, end2);
            String sub2 = subString(str1, end1, str2, end2 - 1);
            return sub1.length() >= sub2.length() ? sub1 : sub2;
        }
    }
}
