package com.tech.solution.leetcode;

/**
 * @author jing1560
 * @data 2024/4/14
 *
 * 算法：跳跃游戏
 *
 * 给你一个非负整数数组 nums ，你最初位于数组的 第一个下标 。数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个下标，如果可以，返回 true ；否则，返回 false 。
 *
 * 示例 1：
 * 输入：nums = [2,3,1,1,4]
 * 输出：true
 * 解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
 *
 * 示例 2：
 * 输入：nums = [3,2,1,0,4]
 * 输出：false
 * 解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
 */
public class JumpGame {

    public static void main(String[] args){
        System.out.println("{2,3,1,1,4} 能否到达最后一个下标：" + canJump(new int[]{2,3,1,1,4}));
        System.out.println("{3,2,1,0,4} 能否到达最后一个下标：" + canJump(new int[]{3,2,1,0,4}));
    }

    private static boolean canJump(int[] nums){
        // 可以跳到的最远距离
        int max_far = 0;
        // 最远右边界
        int end = 0;
        for(int i = 0; i < nums.length - 1; i++){
            max_far = Math.max(max_far, i + nums[i]);
            if(end == i){
                end = max_far;
            }
        }
        if(end >= nums.length - 1){
            return true;
        }
        return false;
    }
}
