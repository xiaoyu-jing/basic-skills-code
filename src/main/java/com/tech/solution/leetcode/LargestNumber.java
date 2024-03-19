package com.tech.solution.leetcode;

import java.util.Arrays;

/**
 * @author jing1560
 * @data 2024/3/19
 * LeetCode  最大数
 */
public class LargestNumber {

    public static void main(String[] args){
        largestNumTet();
    }

    public static void largestNumTet(){
        int[] arr = {1,5,30,3,2,9,7};
        //下面的Arrays.sort方法只能识别 Integer包装类，不能接收int类型，所以做一道转换
        Integer[] newArr = new Integer[arr.length];
        for(int i = 0; i < arr.length; i++){
            newArr[i] = arr[i];
        }
        //自定义排序---将字符串自动排序，首位大的字符串排在第一位
        Arrays.sort(newArr,(a, b) -> {
            String str1 = String.valueOf(a);
            String str2 = String.valueOf(b);
            System.out.println("a=" + a + ",b=" + b);
            return (str2 + str1).compareTo(str1 + str2); //排序为 倒序最大值
//            return (str1 + str2).compareTo(str2 + str1); //排序为 正序最小值
        });

        System.out.println(Arrays.toString(newArr));
        StringBuilder builder = new StringBuilder();
        Arrays.stream(newArr).forEach(str -> builder.append(str));
        System.out.println(builder.toString());
    }
}
