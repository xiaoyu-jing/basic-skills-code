package com.tech.solution.leetcode;

import java.util.Arrays;

/**
 * @author jing1560
 * @data 2024/4/1
 *
 * 算法：零钱兑换
 * 算法类型：动态规划
 *
 * 【题目】给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
 * 你可以认为每种硬币的数量是无限的。
 *
 * 示例 1：
 * 输入：coins = [1, 2, 5], amount = 11
 * 输出：3
 * 解释：11 = 5 + 5 + 1
 *
 * 示例 2：
 * 输入：coins = [2], amount = 3
 * 输出：-1
 *
 */
public class CoinChange {

    public static void main(String[] args){
        /*System.out.println("【暴力求解】最少硬币数为（答案正确）：" + coinChangeSolution1(new int[]{1,2,5}, 11));
        System.out.println("【暴力求解】最少硬币数为（答案错误）：" + coinChangeSolution1(new int[]{186,419,83,408}, 6249));*/

        System.out.println("【动态规划】最少硬币数为：" + coinChangeSolution2(new int[]{1,2,5}, 11));

        System.out.println("【动态规划】最少硬币数为：" + coinChangeSolution2(new int[]{186,419,83,408}, 6249));
        System.out.println("【动态规划】最少硬币数为：" + coinChangeSolution2(new int[]{2}, 3));
    }

    /**
     * 方法一：暴力求解，只能答对一部分测试用例
     * @param coins
     * @param amount
     * @return
     */
    public static int coinChangeSolution1(int[] coins, int amount){
        if(amount == 0){
            return 0;
        }
        int coinCount = 0;
        Arrays.sort(coins);
        while(amount > 0){
            for(int j = coins.length - 1; j >= 0; j--){
                if(amount - coins[j] >= 0){
                    coinCount++;
                    amount = amount - coins[j];
                    break;
                }
            }
            // 防止死循环，当总金额小于数组中的最小值时，表示已经没有硬币可以匹配了
            if(amount < coins[0]){
                break;
            }
        }
        if(amount > 0){
            return -1;
        }
        return coinCount;
    }

    /**
     * 方法二：动态规划
     * 详解：https://blog.csdn.net/LL19880915/article/details/120292953
     *
     * 假如 输入为：coins=[1,2,5]  amount = 11
     *
     * 第一步：如果需要记录这 12 个数 （0 ~ 11），我们就需要一个长度为12的数组来记录每一个值的状态。
     * 第二步：处理标记 0 ~ amount 金额的状态，遍历coins里的所有硬币去计算出最少硬币数。
     * 第三步：返回状态值，处理无法兑换的情况。
     * @param coins
     * @param amount
     * @return
     */
    public static int coinChangeSolution2(int[] coins, int amount){
        // 初始化一个长度为 amount+1 ，用来存储状态的数组，默认值填充为 amount+1,也可以填充为最大值，用来后续比较。
        int[] dp = new int[amount + 1];
        // 给动态规划数组的每一个元素塞一个 大于总金额 的数，此处塞一个 amount + 1
        Arrays.fill(dp, amount + 1);
        // 设置初始0 的最少硬币为0
        dp[0] = 0;
        // i 表示 从 0 ~ amount 的金额，  分别处理标记 0 ~ amount 的状态值
        for(int i = 0; i < dp.length; i++){
            // 用不同的硬币种类去判断各种情况
            for(int j = 0; j < coins.length; j++){
                // 当前总金额 i 大于等于 当前硬币金额 coins[j] 时，才进行逻辑判断； 小于 时，表示值为 负数，直接丢弃
                // 比当前金额大的硬币可以忽略
                if(i - coins[j] >= 0){
                    // dp[i-coins[j]]获得该价格下的最优数量
                    /**
                     * dp[i - coins[j]] + 1   公式中 "+1" 的原因：
                     * 如果最后（包含）一枚的面值 是1，那么 11-1=10 ，我们就需要知道兑换10所需的最少硬币数量dp[10]
                     * 加上我们刚才拿出的那一枚硬币，就得出了兑换11的答案
                     */
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        // 是否是初始化的值 （amount + 1），如果是初始值说明没有兑换成功
        return (dp[amount] == amount + 1) ? -1 : dp[amount];
    }
}
