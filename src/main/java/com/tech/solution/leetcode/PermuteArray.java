package com.tech.solution.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author jing1560
 * @data 2024/4/14
 *
 * 算法：全排列
 *
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 *
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 * 示例 2：
 * 输入：nums = [0,1]
 * 输出：[[0,1],[1,0]]
 *
 * 示例 3：
 * 输入：nums = [1]
 * 输出：[[1]]
 */
public class PermuteArray {

    public static void main(String[] args){
        System.out.println(permute(new int[]{1,2,3}));
        System.out.println(permute(new int[]{0,1}));
        System.out.println(permute(new int[]{1}));
    }

    private static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();
        boolean[] used = new boolean[nums.length];
        dfs(nums, 0, path, used, res);
        return res;
    }

    /**
     * 深度优先遍历
     * @param nums
     * @param depth
     * @param path
     * @param used
     * @param res
     */
    private static void dfs(int[] nums, int depth, Deque<Integer> path, boolean[] used, List<List<Integer>> res){
        if(depth == nums.length){
            res.add(new ArrayList<>(path));
            return;
        }
        for(int i = 0; i < nums.length; i++){
            if(used[i]){
                continue;
            }
            used[i] = true;
            path.addLast(nums[i]);
            dfs(nums, depth + 1, path, used, res);
            path.removeLast();
            used[i] = false;
        }
    }
}
