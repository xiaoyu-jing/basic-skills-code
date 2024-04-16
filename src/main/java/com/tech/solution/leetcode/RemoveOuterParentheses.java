package com.tech.solution.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author jing1560
 * @data 2024/4/16
 *
 * 算法：删除最外层的括号
 *
 * 有效括号字符串为空 ""、"(" + A + ")" 或 A + B ，其中 A 和 B 都是有效的括号字符串，+ 代表字符串的连接。
 * 例如，""，"()"，"(())()" 和 "(()(()))" 都是有效的括号字符串。
 * 如果有效字符串 s 非空，且不存在将其拆分为 s = A + B 的方法，我们称其为原语（primitive），其中 A 和 B 都是非空有效括号字符串。
 * 给出一个非空有效字符串 s，考虑将其进行原语化分解，使得：s = P_1 + P_2 + ... + P_k，其中 P_i 是有效括号字符串原语。
 * 对 s 进行原语化分解，删除分解中每个原语字符串的最外层括号，返回 s 。
 *
 *
 * 示例 1：
 * 输入：s = "(()())(())"
 * 输出："()()()"
 * 解释：
 * 输入字符串为 "(()())(())"，原语化分解得到 "(()())" + "(())"，
 * 删除每个部分中的最外层括号后得到 "()()" + "()" = "()()()"。
 *
 * 示例 2：
 * 输入：s = "(()())(())(()(()))"
 * 输出："()()()()(())"
 * 解释：
 * 输入字符串为 "(()())(())(()(()))"，原语化分解得到 "(()())" + "(())" + "(()(()))"，
 * 删除每个部分中的最外层括号后得到 "()()" + "()" + "()(())" = "()()()()(())"。
 *
 * 示例 3：
 * 输入：s = "()()"
 * 输出：""
 * 解释：
 * 输入字符串为 "()()"，原语化分解得到 "()" + "()"，
 * 删除每个部分中的最外层括号后得到 "" + "" = ""。
 *
 */
public class RemoveOuterParentheses {

    public static void main(String[] args){
        System.out.println(removeOuterParentheses("(()())(())"));
        System.out.println(removeOuterParentheses("(()())(())(()(()))"));
        System.out.println(removeOuterParentheses("()()"));
    }

    public static String removeOuterParentheses(String s) {
        String subTemp = s;
        Deque<String> deque = new ArrayDeque<>();
        List<String> list = new ArrayList<>();
        String[] subArr = subTemp.split("");
        int start = 0;
        for(int i=0; i<subArr.length; i++){
            if("(".equals(subArr[i])){
                deque.offerLast("(");
            }
            if(")".equals(subArr[i])){
                deque.pollLast();
            }
            if(deque.size() == 0){
                String str = subTemp.substring(start,i+1);
                start = i+1;
                list.add(str);
            }
        }
        StringBuilder builder = new StringBuilder();
        list.stream().forEach(str -> {
            str = str.substring(1,str.length()-1);
            builder.append(str);
        });
        return builder.toString();
    }


}
