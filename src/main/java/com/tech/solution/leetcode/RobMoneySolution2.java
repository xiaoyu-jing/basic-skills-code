package com.tech.solution.leetcode;

/**
 * @author jing1560
 * @data 2024/4/1
 *
 * 【算法】：打家劫舍
 *  算法类型：动态规划
 *  题目解法：二维数组 动态规划
 *
 *  一、状态定义
 *  dp[i][0]表示不偷第i间屋子时，考虑[0,i]号屋子的最大收益；dp[i][1]表示偷第i间屋子时，考虑[0,i]号屋子的最大收益。
 *
 *  二、状态转移方程
 *  如果当前屋子不偷，前面一间可偷可不偷，选择能使获利更大的策略；如果当前屋子偷，前面一间绝对不能偷。从而得到状态转移方程。
 *    1）不偷第i间房屋   dp[i][0]=max(dp[i−1][0],dp[i−1][1])
 *    2）偷第i间房屋     dp[i][1]=dp[i−1][0]+nums[i]
 */
public class RobMoneySolution2 {

    public static void main(String[] args){
        System.out.println("【动态规划-二维数组】能偷到的最大总金额为：" + robMaxMoneyTwoDimArr(new int[]{2,7,9,3,1}));
    }

    private static int robMaxMoneyTwoDimArr(int[] nums){
        if(nums == null || nums.length == 0){
            return 0;
        }
        if(nums.length == 1){
            return nums[0];
        }
        int[][] dp = new int[nums.length][2];
        // dp[i][0] 表示不打劫第 i 间房屋所能获得的最大金额，初始化为 0
        dp[0][0] = 0;
        // dp[i][1] 表示打劫第 i 间房屋所能获得的最大金额，初始化就是 第一个房间的值
        dp[0][1] = nums[0];
        for(int i = 1; i < nums.length; i++){
            // 不打劫第 i 间房屋，比较【前一个不打劫的最大值】和【前一个打劫的最大值】，取最大值
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
            // 打劫第 i 间房屋， 用 【前一个不打劫的最大值 + 当前值】得出打劫当前房屋的最大值
            dp[i][1] = dp[i - 1][0] + nums[i];
        }
        // 最终比较两列 的最大值
        return Math.max(dp[nums.length - 1][0], dp[nums.length - 1][1]);
    }

}
