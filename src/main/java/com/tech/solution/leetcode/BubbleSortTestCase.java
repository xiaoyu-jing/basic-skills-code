package com.tech.solution.leetcode;

import java.util.Arrays;

/**
 * @author jing1560
 * @data 2024/3/17
 *
 * 冒泡排序算法  时间复杂度为 O(n^2)
 */
public class BubbleSortTestCase {

    public static void main(String[] args){
        bubbleSortTest1();
    }

    private static void bubbleSortTest1(){
        int[] arr = {7,5,4,9,3,6,8,2,5,1,20};
        System.out.println("数组初始化为：" + Arrays.toString(arr));
        int temp;
        //递减排序
        for(int i = arr.length - 1; i > 0; i--){
            for(int j = 0; j < i; j++){
                if(arr[j] < arr[j+1]){
                    temp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        System.out.println("递减排序后的数组为：" + Arrays.toString(arr));

        //递增排序 - 写法1
        for(int i = arr.length - 1; i > 0; i--){
            for(int j = 0; j < i; j++){
                if(arr[j] > arr[j+1]){
                    temp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        //递增排序 - 写法2
        /*for(int i = 0; i < arr.length; i++){
            for(int j = arr.length - 1; j > 0; j--){
                if(arr[j-1] > arr[j]){
                    temp = arr[j-1];
                    arr[j-1] = arr[j];
                    arr[j] = temp;
                }
            }
        }*/
        System.out.println("递增排序后的数组为：" + Arrays.toString(arr));

    }
}
