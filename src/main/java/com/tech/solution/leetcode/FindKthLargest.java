package com.tech.solution.leetcode;

import java.util.Arrays;

/**
 * @author jing1560
 * @data 2024/3/19
 *
 * 算法：数组中的第K个最大元素
 *
 *  给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 *  请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *  你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
 *
 *  示例 1:
 *  输入: [3,2,1,5,6,4], k = 2
 *  输出: 5
 *
 *  示例 2:
 *  输入: [3,2,3,1,2,4,5,5,6], k = 4
 *  输出: 4
 *
 *  提示：
 *  1 <= k <= nums.length <= 10^5
 *  -10^4 <= nums[i] <= 10^4
 */
public class FindKthLargest {

    public static void main(String[] args){
        System.out.println("{3,2,1,5,6,4} 第 2 个最大元素为：" + findKthLargest1(new int[]{3,2,1,5,6,4}, 2));
        System.out.println("{3,2,3,1,2,4,5,5,6} 第 4 个最大元素为：" + findKthLargest1(new int[]{3,2,3,1,2,4,5,5,6}, 4));

        System.out.println("【桶排序】{3,2,1,5,6,4} 第 2 个最大元素为：" + findKthLargest3(new int[]{3,2,1,5,6,4}, 2));
        System.out.println("【桶排序】{3,2,3,1,2,4,5,5,6} 第 4 个最大元素为：" + findKthLargest3(new int[]{3,2,3,1,2,4,5,5,6}, 4));
    }

    /**
     * 解法一：时间复杂度为 O(nlogn)
     * @param nums
     * @param k
     * @return
     */
    private static int findKthLargest1(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    /**
     * 解法一：时间复杂度为 O(nlogn)
     *        Java8 写法
     * @param nums
     * @param k
     * @return
     */
    private static int findKthLargest2(int[] nums, int k) {
        int result = Arrays.stream(nums).sorted().skip(nums.length - k).min().getAsInt();
        return result;
    }

    /**
     * 解法二：桶排序，时间复杂度为 O(n)
     * @param nums
     * @param k
     * @return
     */
    private static int findKthLargest3(int[] nums, int k){
        //-10^4 <= nums[i] <= 10^4  10^4是10000，前后各10000，一共20000
        int[] buckets = new int[20001];
        for(int i = 0; i < nums.length; i++){
            // 此处取10000，是因为 每一段都是 10^4  10^4是10000
            buckets[nums[i] + 10000]++;
        }
        for(int i = 20000; i >= 0; i--){
            k = k - buckets[i];
            if(k <= 0){
                return i - 10000;
            }
        }
        return 0;
    }

}
