package com.tech.solution.leetcode;

/**
 * @author jing1560
 * @data 2024/4/5
 *
 * 算法：乘积最大子数组
 *
 * 示例 1:
 * 输入: nums = [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 *
 * 示例 2:
 * 输入: nums = [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 */
public class MaxProductSubArray {

    public static void main(String[] args){
        System.out.println("【解法一】{2, 3, -2, 4} 最大子数组的乘积为：" + maxProductSolution1(new int[]{2, 3, -2, 4}));
        System.out.println("【解法一】{-2, 3, -4, 5, -7, -3} 最大子数组的乘积为：" + maxProductSolution1(new int[]{-2, 3, -4, 5, -7, -3}));

        System.out.println("【解法二】{2, 3, -2, 4} 最大子数组的乘积为：" + maxProductSolution2(new int[]{2, 3, -2, 4}));
        System.out.println("【解法二】{-2, 3, -4, 5, -7, -3} 最大子数组的乘积为：" + maxProductSolution2(new int[]{-2, 3, -4, 5, -7, -3}));
    }

    /**
     * 解法一：有报错，但基本可行
     * @param nums
     * @return
     */
    private static int maxProductSolution1(int[] nums){
        if(nums.length == 1){
            return nums[0];
        }
        // 题目要求非空连续子数组（该子数组中至少包含一个数字），所以直接初始化为第一个元素
        int maxValue = nums[0];
        // pre 表示前面值的乘积
        int pre = 1;
        for(int i = 0; i < nums.length; i++){
            //如果前边累乘 后还不如自己本身大，那就把前边的都扔掉，从自己本身重新开始累加。
            //相当于双重for循环跳过了 前面的值
            pre = Math.max(pre * nums[i], nums[i]);
            maxValue = Math.max(pre, maxValue);
        }
        return maxValue;
    }

    /**
     * 解法二：正确解法
     * @param nums
     * @return
     */
    private static int maxProductSolution2(int[] nums){
        if(nums.length == 1){
            return nums[0];
        }
        // 题目要求非空连续子数组（该子数组中至少包含一个数字），所以直接初始化为第一个元素
        int maxValue = nums[0];
        for(int i = 0; i < nums.length; i++){
            // pre 表示前面值的乘积
            int pre = 1;
            for(int j = i; j < nums.length; j++){
                pre *= nums[j];
                maxValue = Math.max(pre, maxValue);
            }
        }
        return maxValue;
    }
}
