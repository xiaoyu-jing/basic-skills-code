package com.tech.solution.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jing1560
 * @data 2024/4/5
 *
 * 算法：最长回文子串
 */
public class LongestPalindrome {

    public static void main(String[] args){
        System.out.println("【暴力解法】babad 的最长回文子串为：" + longestPalindromeSolution1("babad"));

        System.out.println("【暴力解法优化版】babad 的最长回文子串为：" + longestPalindromeSolution2("babad"));
    }

    /**
     * 解法一：暴力解法
     * @param str
     * @return
     */
    private static String longestPalindromeSolution1(String str){
        if(str != null && str.length() < 2){
            return str;
        }
        int maxLength = 0;
        List<String> list = new ArrayList<>();
        for(int i = 0; i < str.length(); i++){
            for(int j = i + 1; j < str.length(); j++){
                String subString = str.substring(i, j);
                StringBuilder builder = new StringBuilder();
                builder.append(subString);
                if(subString.equals(builder.reverse().toString())){
                    if(maxLength < subString.length()){
                        list.add(subString);
                        maxLength = Math.max(maxLength, subString.length());
                    }
                }
            }
        }
        return list.get(list.size() - 1);
    }

    /**
     * 解法二：暴力解法的优化版
     * @param str
     * @return
     */
    private static String longestPalindromeSolution2(String str){
        if(str != null && str.length() < 2){
            return str;
        }
        // 最大回文子串的长度
        int maxLength = 0;
        // 最大回文子串的其实索引号
        int begin = 0;
        char[] strArray = str.toCharArray();
        // 枚举所有长度严格大于 1 的子串 strArray[i...j]
        for(int i = 0; i < str.length() - 1; i++){ // 因为判断的是 (j - i + 1) > maxLength，所以 最外层循环比内层循环 少1即可
            for(int j = i + 1; j < str.length(); j++){
                if((j - i + 1) > maxLength && validPalindromic(strArray, i, j)){
                    begin = i;
                    maxLength = j - i + 1;
                }
            }
        }
        // substring 方法是比较耗时的，所以 只在输出的时候进行 截取
        return str.substring(begin, begin + maxLength);
    }

    /**
     * 校验是否回文串
     * 验证子串 strArray[left...right] 是否为回文串
     * @param strArray
     * @param i
     * @param j
     * @return
     */
    private static boolean validPalindromic(char[] strArray, int left, int right){
        while (left < right){
            if(strArray[left] != strArray[right]){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
