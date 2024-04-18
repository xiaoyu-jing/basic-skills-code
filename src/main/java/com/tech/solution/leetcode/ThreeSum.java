package com.tech.solution.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author jing1560
 * @data 2024/4/14
 *
 * 算法：三数之和
 *
 * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。
 * 请你返回所有和为 0 且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 *
 * 示例 1：
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 * 解释：
 * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
 * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。
 * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。
 * 不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
 * 注意，输出的顺序和三元组的顺序并不重要。
 *
 * 示例 2：
 * 输入：nums = [0,1,1]
 * 输出：[]
 * 解释：唯一可能的三元组和不为 0 。
 *
 * 示例 3：
 * 输入：nums = [0,0,0]
 * 输出：[[0,0,0]]
 * 解释：唯一可能的三元组和为 0 。
 */
public class ThreeSum {

    public static void main(String[] args){
        System.out.println("[-1,0,1,2,-1,-4] 三数之和为0的数组：" + threeSum(new int[]{-1,0,1,2,-1,-4}));
        System.out.println("[0,1,1] 三数之和为0的数组：" + threeSum(new int[]{0,1,1}));
        System.out.println("[0,0,0] 三数之和为0的数组：" + threeSum(new int[]{0,0,0}));
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> resultList = new ArrayList<>();
        Arrays.sort(nums);
        for(int i = 0; i < nums.length; i++){
            //排除重复数
            if(i > 0 && nums[i] == nums[i - 1]){
                continue;
            }
            for(int j = i+1; j < nums.length; j++){
                //排除重复数
                if(j > i+1 && nums[j] == nums[j - 1]){
                    continue;
                }
                for(int k = j+1; k < nums.length; k++){
                    //排除重复数
                    if(k > j+1 && nums[k] == nums[k - 1]){
                        continue;
                    }
                    if(i != j && j != k && i != k && nums[i] + nums[j] + nums[k] == 0){
                        List<Integer> childList = new ArrayList<>();
                        childList.add(nums[i]);
                        childList.add(nums[j]);
                        childList.add(nums[k]);
                        resultList.add(childList);
                    }
                }
            }
        }
        return resultList;
    }

}
