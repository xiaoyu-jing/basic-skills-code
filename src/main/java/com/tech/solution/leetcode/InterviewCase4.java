package com.tech.solution.leetcode;

import java.util.Arrays;

/**
 * @author jing1560
 * @data 2024/3/22
 *
 * 算法：
 * 将两个有序数组合并为一个数组，且该数组有序
 * merge([1, 3, 5], [2, 3, 4, 6]) = [1, 2, 3, 3, 4, 5, 6]
 */
public class InterviewCase4 {

    public static void main(String[] args){
        int[] mergeArr = merge(new int[]{1, 3, 5}, new int[]{2, 3, 4, 6});
        System.out.println(Arrays.toString(mergeArr));
    }

    private static int[] merge(int[] a, int[] b){
        if(a.length == 0 || b.length == 0){
            return null;
        }
        int[] mergeArr = new int[a.length + b.length];
        int arrIndex1 = 0;
        int arrIndex2 = 0;
        int k = 0;
        while (arrIndex1 < a.length && arrIndex2 < b.length) {
            //两个数组的值进行比较
            if(a[arrIndex1] < b[arrIndex2]){
                mergeArr[k++] = a[arrIndex1++];
            } else {
                mergeArr[k++] = b[arrIndex2++];
            }
        }

        /**
         * 后面两个while循环是用来保证两个数组比较完之后剩下的一个数组里的元素能顺利传入
         */
        while (arrIndex1 < a.length) {
            mergeArr[k++] = a[arrIndex1++];
        }
        while (arrIndex2 < b.length) {
            mergeArr[k++] = b[arrIndex2++];
        }

        return mergeArr;
    }
}


