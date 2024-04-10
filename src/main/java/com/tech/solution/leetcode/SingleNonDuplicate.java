package com.tech.solution.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author jing1560
 * @data 2024/4/8
 *
 * 算法：有序数组中的单一元素
 */
public class SingleNonDuplicate {

    public static void main(String[] args){
        System.out.println("{1,1,2,3,3,4,4,8,8} 有序数组中的单一元素为：" + singleNonDuplicateSolution1(new int[]{1,1,2,3,3,4,4,8,8}));
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

}