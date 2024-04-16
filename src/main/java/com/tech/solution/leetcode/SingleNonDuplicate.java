package com.tech.solution.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jing1560
 * @data 2024/4/8
 *
 * 算法：有序数组中的单一元素
 *
 * 给定一个只包含整数的有序数组 nums ，每个元素都会出现两次，唯有一个数只会出现一次，请找出这个唯一的数字。
 *
 * 示例 1:
 * 输入: nums = [1,1,2,3,3,4,4,8,8]
 * 输出: 2
 *
 * 示例 2:
 * 输入: nums =  [3,3,7,7,10,11,11]
 * 输出: 10
 */
public class SingleNonDuplicate {

    public static void main(String[] args){
        System.out.println("{1,1,2,3,3,4,4,8,8} 有序数组中的单一元素为：" + singleNonDuplicateSolution1(new int[]{1,1,2,3,3,4,4,8,8}));

        System.out.println("{1,1,2,3,3,4,4,8,8} 有序数组中的单一元素为：" + singleNonDuplicateSolution2(new int[]{1,1,2,3,3,4,4,8,8}));
    }

    /**
     * 解法一：栈
     * @param nums
     * @return
     */
    private static int singleNonDuplicateSolution1(int[] nums){
        Deque<Integer> stackDeque = new ArrayDeque<>();
        for(int i = 0; i < nums.length; i++){
            if(stackDeque.contains(nums[i])){
                // 将 栈 的头部元素取出，并移除
                stackDeque.pollFirst();
            } else {
                // 将元素添加到栈的头部
                stackDeque.addFirst(nums[i]);
            }
        }
        // 将 栈 的头部元素取出，不移除
        return stackDeque.peekFirst();
    }

    /**
     * 解法二：在 Map 中放一个计数器，然后遍历MAP，哪个值 等于 1，就直接返回
     * @param nums
     * @return
     */
    private static int singleNonDuplicateSolution2(int[] nums){
        Map<Integer, Integer> freq = new HashMap<>();
        for(int num : nums){
            freq.put(num, freq.getOrDefault(num, 0)  + 1);
        }
        int ans = 0;
        for(Map.Entry<Integer, Integer> entry : freq.entrySet()){
            int num = entry.getKey(), occ = entry.getValue();
            if(occ == 1){
                ans = num;
                break;
            }
        }
        return ans;
    }

}
