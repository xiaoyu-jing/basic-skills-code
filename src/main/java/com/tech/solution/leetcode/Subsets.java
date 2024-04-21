package com.tech.solution.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jing1560
 * @data 2024/4/21
 *
 * 算法：子集
 */
public class Subsets {

    public static void main(String[] args){

    }

    public List<List<Integer>> subsets(int[] nums) {
        backtrack(nums, 0);
        return resultList;
    }

    List<List<Integer>> resultList = new ArrayList<>();
    List<Integer> list = new ArrayList<>();

    public void backtrack(int[] nums, int start){
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
