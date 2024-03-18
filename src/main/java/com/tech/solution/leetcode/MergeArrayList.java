package com.tech.solution.leetcode;

import java.util.Arrays;

/**
 * @author jing1560
 * @data 2024/3/18
 * 将两个有序数组合并为一个数组，且该数组有序
 */
public class MergeArrayList {

    public static void main(String[] args){
        int arr1[] = {1,2,2,3,5,6,7,7};
        int arr2[] = {1,2,4,5,8,8,9,10,11,12,12,13,14};
        int[] resultArrList = mergeArrayList(arr1, arr2);
        System.out.println(Arrays.toString(resultArrList));
    }

    private static int[] mergeArrayList(int[] arr1, int[] arr2){
        //i:用于表示 arr1 数组    j：用来表示 arr2 数组  k：用来表示结果数组
        int i = 0;
        int j = 0;
        int k = 0;
        int[] resultArr = new int[arr1.length + arr2.length];
        if(checkSort(arr1) || checkSort(arr2)){
            //两个数组的值进行比较
            while (i < arr1.length && j < arr2.length){
                if(arr1[i] < arr2[j]){
                    resultArr[k++] = arr1[i++];
                }else {
                    resultArr[k++] = arr2[j++];
                }
            }

            /**
             * 后面两个while循环是用来保证两个数组比较完之后剩下的一个数组里的元素能顺利传入
             */
            //剩余数组内容直接放入resultArr中
            while (i < arr1.length){
                resultArr[k++] = arr1[i++];
            }
            while (j < arr2.length){
                resultArr[k++] = arr2[j++];
            }
        } else {
            System.out.println("传入的数组不是有序的！");
        }
        return resultArr;
    }

    /**
     * 校验数组是否递增排序
     *
     * PS：此处有个待优化的问题：目前只有递增的数组才算有序，递减的不算有序，这是不合理的，此处待优化
     * @param arr
     * @return
     */
    private static boolean checkSort(int[] arr){
        //这个标志位是一种 冒泡排序的优化方法
        boolean checkflag = true;
        for(int i = 0; i < arr.length - 1 && checkflag; i++){
            for(int j = i+1; j < arr.length; j++){
                if(arr[j-1] > arr[j]){
                    return false;
                } else {
                    checkflag = false;
                }
            }
        }
        return true;
    }
}
