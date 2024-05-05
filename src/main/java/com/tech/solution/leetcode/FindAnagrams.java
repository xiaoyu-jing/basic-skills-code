package com.tech.solution.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author jing1560
 * @data 2024/5/5
 *
 * 算法：
 */
public class FindAnagrams {

    public static void main(String[] args){
        System.out.println(findAnagramsSolution3("cbaebabacd", "abc"));
    }

    public static List<Integer> findAnagramsSolution3(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if(s.length() < p.length()){
            return res;
        }
        char[] pArr = p.toCharArray();
        Arrays.sort(pArr);
        for(int i = 0; i <= s.length() - p.length(); i++){
            char[] srcArr = s.toCharArray();
            Arrays.sort(srcArr, i, i + p.length());
            int k = 0;
            for(int j = i; j < i + p.length(); j++){
                if(srcArr[j] != pArr[k]){
                    k = 0;
                    break;
                }
                k++;
            }
            if(k > 0){
                res.add(i);
            }
        }
        return res;
    }
}
