package com.tech.solution.leetcode;

/**
 * @author jing1560
 * @data 2024/4/28
 *
 * 算法：字节算法【非 LeetCode 原题】
 *
 * 数组a,先单调递增再单调递减，输出数组中不同元素个数。要求：O(1)空间复杂度，不能改变原数组
 *
 * 示例：
 * 输入：[1,2,3,4,5,4,3,2,1]
 * 输出：5
 */
public class CountUniqueElements {

    public static void main(String[] args){
        System.out.println(countUniqueElements2(new int[]{1,2,3,4,5,4,3,2,1})); // 输出 5

        System.out.println(countUniqueElements2(new int[]{2,2,6,11,20,20,25,30,37,35,33,33,30,20,12,3})); // 输出 11
    }

    /**
     * 解法 1：百度搜索的答案
     * @param nums
     * @return
     */
    public static int countUniqueElements1(int[] nums) {
        int n = nums.length;
        if(n == 0 || nums == null){
            return 0;
        }
        int left = 0;
        int right = n - 1;
        int sum = 0;
        while(left <= right){
            if(nums[left] == nums[right]){
                sum++;
                int temp = nums[left];
                while(left <= right && nums[right] == temp)
                    right--;
                while(left <= right && nums[left] == temp)
                    left++;
            } else if(nums[left] < nums[right]){
                sum++;
                int temp = nums[left];
                while(left <= right && nums[left] == temp)
                    left++;
            } else{
                sum++;
                int temp = nums[right];
                while(left <= right && nums[right] == temp)
                    right--;
            }
        }
        return sum;
    }

    /**
     * 解法 2：牛客的网友答案
     *
     * 双指针法，每次取最小元素移动，不使用额外空间，O(n)
     *
     * {2,2,6,11,20,20,25,30,37,35,33,33,30,20,12,3}
     * @param nums
     * @return
     */
    public static int countUniqueElements2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int l = 0, r = nums.length - 1;
        int count = 0;
        while (l <= r) {
            if (nums[l] < nums[r]) {
                count++;
                l++;
            } else if (nums[l] > nums[r]) {
                count++;
                r--;
            } else {
                l++;
                r--;
                count++;
            }
        }
        return count;
    }

}
