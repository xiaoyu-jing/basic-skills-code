package com.tech.solution.leetcode;

import java.util.Arrays;

/**
 * @author jing1560
 * @data 2024/4/15
 *
 * 算法：滑动窗口最大值
 *
 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * 返回 滑动窗口中的最大值 。
 *
 * 示例 1：
 * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
 * 输出：[3,3,5,5,6,7]
 * 解释：
 * 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 *
 * 示例 2：
 * 输入：nums = [1], k = 1
 * 输出：[1]
 */
public class MaxSlidingWindow {

    public static void main(String[] args){
        System.out.println(Arrays.toString(maxSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7}, 3)));
        System.out.println(Arrays.toString(maxSlidingWindow(new int[]{1}, 1)));
    }

    private static int[] maxSlidingWindow(int[] nums, int k) {
        // 从题目中推断，数组长度为8，k为3，返回的中位数个数为 8-3+1
        int[] maxArr = new int[nums.length - k + 1];
        // 定义单次滑动窗口数组
        int[] slidingWindowArr = new int[k];
        for(int i = 0; i <= nums.length - k; i++){
            System.arraycopy(nums, i, slidingWindowArr, 0, k);
            Arrays.sort(slidingWindowArr);
            maxArr[i] = slidingWindowArr[slidingWindowArr.length - 1];
        }
        return maxArr;
    }
}
