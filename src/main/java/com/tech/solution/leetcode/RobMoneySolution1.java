package com.tech.solution.leetcode;

/**
 * @author jing1560
 * @data 2024/3/31
 *
 * 【算法】：打家劫舍
 * 算法类型：动态规划
 * 题目解法：一维数组 动态规划
 *
 * 首先考虑最简单的情况。如果只有一间房屋，则偷窃该房屋，可以偷窃到最高总金额。如果只有两间房屋，则由于两间房屋相邻，不能同时偷窃，只能偷窃其中的一间房屋，因此选择其中金额较高的房屋进行偷窃，可以偷窃到最高总金额。
 * 如果房屋数量大于两间，应该如何计算能够偷窃到的最高总金额呢？对于第 k(k>2)间房屋，有两个选项：
 *      ①　偷窃第 k 间房屋，那么就不能偷窃第 k−1 间房屋，偷窃总金额为前 k−2 间房屋的最高总金额与第 k 间房屋的金额之和。
 *      ②　不偷窃第 k 间房屋，偷窃总金额为前 k−1 间房屋的最高总金额。
 *
 * 在两个选项中选择偷窃总金额较大的选项，该选项对应的偷窃总金额即为前 k 间房屋能偷窃到的最高总金额。
 * 用 dp[i] 表示前 i 间房屋能偷窃到的最高总金额，那么就有如下的状态转移方程：
 *              dp[i] = max(dp[i−2] + nums[i], dp[i−1])
 *
 * 边界条件为：
 *      丨 dp[0]=nums[0]                     只有一间房屋，则偷窃该房屋
 *      丨 dp[1]=max(nums[0],nums[1])        只有两间房屋，选择其中金额较高的房屋进行偷窃
 *
 * 最终的答案即为 dp[n−1]，其中 n 是数组的长度。
 *
 * 【题目分析】
 * - 第 1 间房屋：S0 = H0 = 1
 * - 前 2 间房屋：S1 = max(S0, H1) = 2
 * - 前 3 间房屋：S2 = max(S1, S0 + H2) = 4
 * - 前 4 间房屋：S3 = max(S2, S1 + H3) = 4
 * - 递推公式：   Sn = max(Sn-1, Sn-2 + Hn)
 *     偷窃前 n-1 间房屋的最高金额  或  偷窃前 n-2 间房屋的最高金额 + 第 n 间房屋的金额
 *
 */
public class RobMoneySolution1 {

    public static void main(String[] args){
        System.out.println("【动态规划-一维数组】能偷到的最大总金额为：" + robMaxMoney(new int[]{1,2,3,1}));

        System.out.println("【简单版-奇数偶数】能偷到的最大总金额为：" + robMaxMoneySimple(new int[]{1,2,3,1}));
    }

    /**
     * 动态规划
     * @param nums
     * @return
     */
    private static int robMaxMoney(int[] nums){
        if(nums == null || nums.length == 0){
            return 0;
        }
        int length = nums.length;
        if(length == 1){
            return nums[0];
        }
        int[] dp = new int[length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for(int i = 2; i < length; i++){
            // 状态转移方程 - 递推公式
            // 偷窃前 n-1 间房屋的最高金额 或 偷窃前 n-2 间房屋的最高金额 加 第n间房屋的金额
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[length - 1];
    }

    /**
     * 简版做法，不提倡，只能通过一部分用例 ！！！
     *
     * 通过 奇数 和 偶数 进行求和计算
     * @param nums
     * @return
     */
    private static int robMaxMoneySimple(int[] nums){
        if(nums == null || nums.length == 0){
            return 0;
        }
        if(nums.length == 1){
            return nums[0];
        }
        int ouShuMax = 0;
        int jiShuMax = 0;
        for(int i = 0; i < nums.length; i++){
            // 偶数位求和
            if(i % 2 == 0){
                ouShuMax += nums[i];
            } else {
                // 奇数位求和
                jiShuMax += nums[i];
            }
        }
        return Math.max(ouShuMax, jiShuMax);
    }

}
