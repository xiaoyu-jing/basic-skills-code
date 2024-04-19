package com.tech.solution.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jing1560
 * @data 2024/4/19
 *
 * 算法：151.反转字符串中的单词
 *
 * 给你一个字符串 s ，请你反转字符串中 单词 的顺序。
 * 单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。
 * 返回 单词 顺序颠倒且 单词 之间用单个空格连接的结果字符串。
 * 注意：输入字符串 s中可能会存在前导空格、尾随空格或者单词间的多个空格。返回的结果字符串中，单词间应当仅用单个空格分隔，且不包含任何额外的空格。
 *
 *
 * 示例 1：
 * 输入：s = "the sky is blue"
 * 输出："blue is sky the"
 *
 * 示例 2：
 * 输入：s = "  hello world  "
 * 输出："world hello"
 * 解释：反转后的字符串中不能存在前导空格和尾随空格。
 *
 * 示例 3：
 * 输入：s = "a good   example"
 * 输出："example good a"
 * 解释：如果两个单词间有多余的空格，反转后的字符串需要将单词间的空格减少到仅有一个。
 *
 *
 * 提示：
 * 1 <= s.length <= 10^4
 * s 包含英文大小写字母、数字和空格 ' '
 * s 中 至少存在一个 单词
 *
 */
public class ReverseWords {

    public static void main(String[] args){
        System.out.println(reverseWords("the sky is blue"));
        System.out.println(reverseWords("  hello world  "));
        System.out.println(reverseWords("a good   example"));   // s.split(" "); 的结果为：["a", "good", "","", "example"]
    }

    public static String reverseWords(String s) {
//        s = s.trim();   // trim()方法可以去除前后空格，但在该方法中意义不大，可以去掉
        String[] strArr = s.split(" ");
        List<String> list = new ArrayList<>();
        for(String str : strArr){
            if(str.equals("")){
                continue;
            }
            list.add(str);
        }
        StringBuilder builder = new StringBuilder();
        if(!list.isEmpty()){
            for(int i = list.size() - 1; i >=0; i--){
                if(builder.length() > 0){
                    builder.append(" ");
                }
                builder.append(list.get(i));
            }
            return builder.toString();
        }
        return s;
    }
}
