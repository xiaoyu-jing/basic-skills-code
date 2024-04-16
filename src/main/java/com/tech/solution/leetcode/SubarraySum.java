package com.tech.solution.leetcode;

/**
 * @author jing1560
 * @data 2024/4/16
 *
 * 算法：和为K的子数组
 *
 * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。
 * 子数组是数组中元素的连续非空序列。
 *
 * 示例 1：
 * 输入：nums = [1,1,1], k = 2
 * 输出：2
 *
 * 示例 2：
 * 输入：nums = [1,2,3], k = 3
 * 输出：2
 */
public class SubarraySum {

    public static void main(String[] args){
        System.out.println(subarraySum(new int[]{1,1,1}, 2));
        System.out.println(subarraySum(new int[]{1,2,3}, 3));
        System.out.println(subarraySum(new int[]{1,-1,0}, 0)); // 该用例比较特殊，输出 3
    }

    private static int subarraySum(int[] nums, int k) {
        int arrayCount = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == k){
                arrayCount++;
            }
            int arrSum = nums[i];
            for(int j = i + 1; j < nums.length; j++){
                arrSum += nums[j];
                if(arrSum == k){
                    arrayCount++;
                }
            }
        }
        return arrayCount;
    }
}
