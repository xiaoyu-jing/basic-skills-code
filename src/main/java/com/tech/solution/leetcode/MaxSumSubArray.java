package com.tech.solution.leetcode;

/**
 * @author jing1560
 * @data 2024/3/25
 *
 * 算法：最大子数组和 --- 动态规划
 *
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 子数组是数组中的一个连续部分。
 *
 * 示例 1：
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
 *
 * 示例 2：
 * 输入：nums = [1]
 * 输出：1
 *
 * 示例 3：
 * 输入：nums = [5,4,-1,7,8]
 * 输出：23
 */
public class MaxSumSubArray {

    public static void main(String[] args){
        System.out.println("解法1：最大子串的和值为：" + maxSumSubArraySolution1(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
        System.out.println("解法1：最大子串的和值为：" + maxSumSubArraySolution1(new int[]{1}));
        System.out.println("解法1：最大子串的和值为：" + maxSumSubArraySolution1(new int[]{5,4,-1,7,8}));

        System.out.println("解法2：最大子串的和值为：" + maxSumSubArraySolution2(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
        System.out.println("解法2：最大子串的和值为：" + maxSumSubArraySolution2(new int[]{1}));
        System.out.println("解法2：最大子串的和值为：" + maxSumSubArraySolution2(new int[]{5,4,-1,7,8}));
    }

    /**
     * 解法1：LeetCode上，只有极少部分用例无法通过
     * @param nums
     * @return
     */
    public static int maxSumSubArraySolution1(int[] nums){
        //maxSum不要从0开始，防止nums中都是负数，出现 0 最大的情况
        //如，输入：nums=[-2,-1], 输出：-1  （不应该是0）
        int maxSum = nums[0];
        for(int i = 0; i < nums.length; i++){
            int sum = nums[i];
            for(int j = i + 1; j < nums.length; j++){
                sum += nums[j];
                maxSum = Math.max(maxSum,sum);
            }
        }
        return maxSum;
    }

    /**
     * 解法2 : 优化解法
     * @param nums
     * @return
     */
    public static int maxSumSubArraySolution2(int[] nums){
        int pre = 0;
        int maxSum = nums[0];
        for(int x : nums){
            //如果前边累加后还不如自己本身大，那就把前边的都扔掉，从自己本身重新开始累加。
            //相当于双重for循环跳过了 前面的值
            pre = Math.max(pre + x , x);
            maxSum = Math.max(maxSum,pre);
        }
        return maxSum;
    }
}
