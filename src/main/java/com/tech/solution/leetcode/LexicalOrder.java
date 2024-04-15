package com.tech.solution.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author jing1560
 * @data 2024/4/15
 *
 * 算法：字典序排数
 *
 * 给你一个整数 n ，按字典序返回范围 [1, n] 内所有整数。
 * 你必须设计一个时间复杂度为 O(n) 且使用 O(1) 额外空间的算法。
 *
 * 示例 1：
 * 输入：n = 13
 * 输出：[1,10,11,12,13,2,3,4,5,6,7,8,9]
 *
 * 示例 2：
 * 输入：n = 2
 * 输出：[1,2]
 */
public class LexicalOrder {

    public static void main(String[] args){
        System.out.println("13 的字典序排数为：" + lexicalOrder(13));
        System.out.println("2 的字典序排数为：" + lexicalOrder(2));
    }

    private static List<Integer> lexicalOrder(int n) {
        List<String> list = new ArrayList<>();
        for(int i = 1; i <= n; i++){
            list.add(String.valueOf(i));
        }
        Collections.sort(list);
        List<Integer> resultList = new ArrayList<>();
        list.stream().forEach(num -> resultList.add(Integer.valueOf(num)));
        return resultList;
    }
}
