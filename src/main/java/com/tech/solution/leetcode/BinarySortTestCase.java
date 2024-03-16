package com.tech.solution.leetcode;

import java.util.Arrays;

/**
 * @author jing1560
 * @data 2024/3/16
 * 二分排序，从10个数中找到3
 */
public class BinarySortTestCase {

    public static void main(String[] args){
//        int[] arr = {1,2,3,4,5,6,7,8,9,10};
        int[] arr = {1,2,9,4,5,6,7,8,3,10};
        int key = 3;
        int keyIndex = binarySort(arr, key);
        if(keyIndex == -1){
            System.out.printf("在数组 %s 中没有找到 %s",Arrays.toString(arr) , key);
        } else {
            System.out.printf("在数组 %s 中找到了 %s,索引为 %s", Arrays.toString(arr),key,keyIndex);
        }
    }

    private static int binarySort(int[] arr, int key){
        Arrays.sort(arr);
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if(key == arr[mid]){
                return mid;
            } else if (key < arr[mid]){
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }
}
