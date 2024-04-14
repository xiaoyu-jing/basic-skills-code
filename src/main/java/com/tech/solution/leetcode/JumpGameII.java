package com.tech.solution.leetcode;

/**
 * @author jing1560
 * @data 2024/4/14
 *
 * 算法：跳跃游戏II
 *
 * 给定一个长度为 n 的 0 索引整数数组 nums。初始位置为 nums[0]。
 * 每个元素 nums[i] 表示从索引 i 向前跳转的最大长度。换句话说，如果你在 nums[i] 处，你可以跳转到任意 nums[i + j] 处:
 * 0 <= j <= nums[i]
 * i + j < n
 * 返回到达 nums[n - 1] 的最小跳跃次数。生成的测试用例可以到达 nums[n - 1]。
 *
 *
 * 示例 1:
 * 输入: nums = [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 *      从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 *
 * 示例 2:
 * 输入: nums = [2,3,0,1,4]
 * 输出: 2
 */
public class JumpGameII {

    public static void main(String[] args){
        System.out.println(jump(new int[]{2,3,1,1,4}));
        System.out.println(jump(new int[]{2,3,0,1,4}));
    }

    /**
     1、判断每一步能跳到的最远位置，作为右边界
     2、在上述的边界区域内，寻找能跳到的 下一个最远位置
     3、循环指针与第一步的右边界重合时，跳跃次数+1
     4、重复循环
     */
    private static int jump(int[] nums){
        // 跳跃次数
        int jumpNum = 0;
        // 能跳到的最远位置
        int maxFar = 0;
        // 跳跃的最右边界
        int end = 0;
        for(int i = 0; i < nums.length - 1; i++){
            maxFar = Math.max(maxFar, nums[i] + i);
            if(end == i){
                end = maxFar;
                jumpNum++;
            }
        }
        return jumpNum;
    }
}
