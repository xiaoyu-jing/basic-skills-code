package com.tech.solution.leetcode;

import java.util.*;

/**
 * @author jing1560
 * @data 2024/4/15
 *
 * 算法：字母异位词分组
 *
 * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
 * 字母异位词 是由重新排列源单词的所有字母得到的一个新单词。
 *
 * 示例 1:
 * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
 *
 * 示例 2:
 * 输入: strs = [""]
 * 输出: [[""]]
 *
 * 示例 3:
 * 输入: strs = ["a"]
 * 输出: [["a"]]
 */
public class GroupAnagrams {

    public static void main(String[] args){
        System.out.println(groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
        System.out.println(groupAnagrams(new String[]{""}));
        System.out.println(groupAnagrams(new String[]{"a"}));
    }

    private static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for(String str : strs){
            String[] childStrArr = str.split("");
            Arrays.sort(childStrArr);
            String joinStr = Arrays.toString(childStrArr);
            List<String> codeList = null;
            if(map.get(joinStr) != null){
                codeList = map.get(joinStr);
            } else {
                codeList = new ArrayList<>();
            }
            codeList.add(str);
            map.put(joinStr,codeList);
        }
        //遍历集合获取元素
        List<List<String>> resultList = new ArrayList<>();
        Iterator iterator =  map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry)iterator.next();
            List<String> childList = (List<String>)entry.getValue();
            resultList.add(childList);
        }
        return resultList;
    }
}
