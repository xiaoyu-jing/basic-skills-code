package com.tech.solution.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author jing1560
 * @data 2024/5/5
 *
 * 算法：找到字符串中所有字母异位词
 *
 * 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 * 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
 *
 * 示例 1:
 * 输入: s = "cbaebabacd", p = "abc"
 * 输出: [0,6]
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
 *
 *  示例 2:
 * 输入: s = "abab", p = "ab"
 * 输出: [0,1,2]
 * 解释:
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
 *
 * 提示:
 * 1 <= s.length, p.length <= 3 * 104
 * s 和 p 仅包含小写字母
 */
public class FindAnagrams {

    public static void main(String[] args){
        System.out.println(findAnagramsSolution3("cbaebabacd", "abc"));

        System.out.println(findAnagramsSolution3("abab", "ab"));
    }

    /**
     解法一：暴力解法（会超出时间限制）
     */
    public List<Integer> findAnagramsSolution1(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if(s.length() < p.length()){
            return res;
        }
        String[] pArr = p.split("");
        Arrays.sort(pArr);
        String defaultP = Arrays.toString(pArr);
        for(int i = 0; i <= s.length() - p.length(); i++){
            String childStr = s.substring(i, i + p.length());
            String[] childArr = childStr.split("");
            Arrays.sort(childArr);
            if(Arrays.toString(childArr).equals(defaultP)){
                res.add(i);
            }
        }
        return res;
    }

    /**
     解法二：暴力解法（会超出时间限制）
     */
    public List<Integer> findAnagramsSolution2(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if(s.length() < p.length()){
            return res;
        }
        String[] pArr = p.split("");
        Arrays.sort(pArr);
        String defaultP = Arrays.toString(pArr);
        String[] srcArr = s.split("");
        for(int i = 0; i <= s.length() - p.length(); i++){
            String[] childArr = new String[p.length()];
            System.arraycopy(srcArr, i, childArr, 0, p.length());
            Arrays.sort(childArr);
            if(Arrays.toString(childArr).equals(defaultP)){
                res.add(i);
            }
        }
        return res;
    }

    /**
     * 解法三：简易指针（耗时较长）
     * @param s
     * @param p
     * @return
     */
    public static List<Integer> findAnagramsSolution3(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if(s.length() < p.length()){
            return res;
        }
        // p 字符串进行字典序排序
        char[] pArr = p.toCharArray();
        Arrays.sort(pArr);
        for(int i = 0; i <= s.length() - p.length(); i++){
            // s 字符串按照指定索引进行字典序排序
            char[] srcArr = s.toCharArray();
            Arrays.sort(srcArr, i, i + p.length());
            // 临时指针计数器
            int k = 0;
            for(int j = i; j < i + p.length(); j++){
                if(srcArr[j] != pArr[k]){
                    k = 0;
                    break;
                }
                k++;
            }
            // k 大于 则表示存在符合条件的字母异位词
            if(k > 0){
                res.add(i);
            }
        }
        return res;
    }

}
