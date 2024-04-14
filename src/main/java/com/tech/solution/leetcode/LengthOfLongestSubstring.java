package com.tech.solution.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author jing1560
 * @data 2024/4/14
 *
 * 算法：无重复字符的最长子串
 *
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串的长度。
 *
 * 示例 1:
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 * 示例 2:
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 *
 * 示例 3:
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 */
public class LengthOfLongestSubstring {

    public static void main(String[] args){
        System.out.println("解法一 栈 ：" + lengthOfLongestSubstring1("abcabcbb"));
        System.out.println("解法一 栈 ：" + lengthOfLongestSubstring1("bbbbb"));
        System.out.println("解法一 栈 ：" + lengthOfLongestSubstring1("pwwkew"));

        System.out.println("解法二 集合 ：" + lengthOfLongestSubstring2("abcabcbb"));
        System.out.println("解法二 集合 ：" + lengthOfLongestSubstring2("bbbbb"));
        System.out.println("解法二 集合 ：" + lengthOfLongestSubstring2("pwwkew"));
    }

    /**
     * 解法一：栈
     * @param s
     * @return
     */
    private static int lengthOfLongestSubstring1(String s){
        if(s.length() == 1){
            return 1;   // 应对 LeetCode 上的特殊场景
        }
        int maxLength = 0;
        Deque<Character> deque = new ArrayDeque<>();
        for(int i = 0; i < s.length(); i++){
            for(int j = i; j < s.length(); j++){
                if(!deque.isEmpty() && deque.contains(s.charAt(j))){
                    break;
                }
                deque.addLast(s.charAt(j));
            }
            // 在清理之前进行计数
            maxLength = Math.max(maxLength, deque.size());
            deque.clear();
        }
        return maxLength;
    }

    /**
     * 解法二：集合
     * @param s
     * @return
     */
    private static int lengthOfLongestSubstring2(String s){
        if(s.length() == 0){
            return 0;  // 应对 LeetCode 上的特殊场景
        }
        String[] strArr = s.split("");
        List<String> list = new ArrayList<>();
        int maxLength = 0;
        for(int i = 0; i < strArr.length; i++){
            for(int j = i; j < strArr.length; j++){
                if(list.contains(strArr[j])){
                    break;
                }
                list.add(strArr[i]);
            }
            // 在清理之前进行计数
            maxLength = Math.max(maxLength, list.size());
            list.clear();
        }
        return maxLength;
    }
}
