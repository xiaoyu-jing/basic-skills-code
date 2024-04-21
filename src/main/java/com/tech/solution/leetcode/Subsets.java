package com.tech.solution.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jing1560
 * @data 2024/4/21
 *
 * 算法：子集
 *
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集(幂集）。
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 *
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 *
 * 示例 2：
 * 输入：nums = [0]
 * 输出：[[],[0]]
 */
public class Subsets {

    public static void main(String[] args){
        System.out.println(subsets(new int[]{1,2,3}));
    }

    public static List<List<Integer>> subsets(int[] nums) {
        backtrack(nums, 0);
        return resultList;
    }

    static List<List<Integer>> resultList = new ArrayList<>();
    static List<Integer> list = new ArrayList<>();

    public static void backtrack(int[] nums, int start){
        resultList.add(new ArrayList<>(list));
        if(start >= nums.length){
            return;
        }
        for(int i = start; i < nums.length; i++){
            list.add(nums[i]);
            backtrack(nums, i + 1);
            list.remove(list.size() - 1);
        }
    }
}
