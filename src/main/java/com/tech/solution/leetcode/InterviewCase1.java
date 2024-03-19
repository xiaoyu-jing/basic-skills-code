package com.tech.solution.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jing1560
 * @data 2024/3/19
 * 瓜子二手车 一面算法
 */
public class InterviewCase1 {

    /**
     * 算法：
     * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。1 <= n <= 20，1 <= k <= n。
     * 你可以按 任何顺序 返回答案。
     * 示例 1：
     * 输入：n = 4, k = 2
     * 输出：
     * [
     *   [2,4],
     *   [3,4],
     *   [2,3],
     *   [1,2],
     *   [1,3],
     *   [1,4],
     * ]
     * 示例 2：
     * 输入：n = 1, k = 1
     * 输出：[[1]]
     */

    public static void main(String[] args){
        System.out.println(valueArr(4,2));
        System.out.println(valueArr(1,1));
    }

    private static List<List<Integer>> valueArr(int n, int k){
        List<List<Integer>> parentList = new ArrayList<>();
        if(n < k){
            return parentList;
        }

        if(n == 1 && k == 1){
            List<Integer> list = new ArrayList<>();
            list.add(1);
            parentList.add(list);
        }

        int[] defaultArr = new int[n];
        for(int i = 0; i < n; i++){
            defaultArr[i] = i+1;
        }
        //[1,2,3,4]
        for(int j = 0; j < defaultArr.length; j++){
            for(int p = j+1; p < defaultArr.length; p++){
                List<Integer> childList = new ArrayList<>();
                childList.add(defaultArr[j]);
                childList.add(defaultArr[p]);
                parentList.add(childList);
            }
        }

        return  parentList;
    }

}
