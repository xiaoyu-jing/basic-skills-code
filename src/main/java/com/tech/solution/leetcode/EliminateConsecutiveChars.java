package com.tech.solution.leetcode;

import java.util.Stack;

/**
 * @author jing1560
 * @data 2024/4/23
 *
 * 算法：字节二面算法【非 LeetCode 原题】
 *
 * 标题
 * 消除游戏，给定一个字符串，消除其中连续的字符
 *
 * 题目描述
 * 举例：
 * 1. 'rgb' => 'rgb'
 * 2. 'rggb' => 'rb'
 * 3. rggggb' => 'rb'
 * 4. 'rggggrrb' => 'rrrb' => 'b'
 * 5. 'bbrggggrrb' => ...（过程省略）=> ''
 * 6. 'bbrggggrrbr' => 'r'
 *
 * 注意：
 * 1. 优先消除最长的情况
 * 2. 长度一样，优先消除左侧
 * 3. 不强要求复杂度
 */
public class EliminateConsecutiveChars {

    public static void main(String[] args){
        String[] testCases = {
          "rgb",
          "rggb",
          "rggggb",
          "rggggrrb",
          "bbrggggrrb",
          "bbrggggrrbr"
        };
        for(String testCase : testCases){
            System.out.println(testCase + " => " + eliminateConsecutiveChars2(testCase));
        }
    }

    private static String eliminateConsecutiveChars1(String str){
        Stack<Character> stack = new Stack<>();
        int maxCount = 0;
        char currentChar = 0;
        int currentCount = 0;

        for(int i = 0; i < str.length(); i++){
            char c = str.charAt(i);
            if(c == currentChar){
                currentCount++;
                maxCount = Math.max(maxCount, currentCount);
            } else {
                if (currentCount > 0) {
                    // 如果当前字符有连续的，将其压入栈中，并保留最长的连续次数
                    for(int j = 0; j < maxCount; j++){
                        stack.push(currentChar);
                    }
                }
                currentChar = c;
                currentCount = 1;
                maxCount = 1;
            }
        }

        // 处理最后一个字符的连续情况
        if(currentCount > 0){
            for(int j = 0; j < maxCount; j++){
                stack.push(currentChar);
            }
        }

        StringBuilder builder = new StringBuilder();
        while (!stack.isEmpty()) {
            builder.append(stack.pop());
        }
        return builder.reverse().toString();
    }

    private static String eliminateConsecutiveChars2(String str){
        StringBuilder result = new StringBuilder();
        int maxCount = 1;
        int currentCount = 1;
        int startIndex = 0;

        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) == str.charAt(i - 1)) {
                // 当前字符与前一个字符相同，增加计数
                currentCount++;
                if (currentCount > maxCount) {
                    // 发现更长的连续序列，更新最大值和起始索引
                    maxCount = currentCount;
                    startIndex = i - maxCount;
                }
            } else {
                // 当前字符与前一个字符不同，检查是否需要消除之前的连续序列
                if (maxCount > 1) {
                    // 如果连续序列长度大于1，则只保留一个字符
                    result.append(str.charAt(startIndex + maxCount - 1));
                }
                // 重置计数和起始索引
                maxCount = 1;
                currentCount = 1;
                startIndex = i - 1;
            }
        }

        // 处理字符串末尾的连续序列
        if (maxCount > 1) {
            result.append(str.charAt(startIndex + maxCount - 1));
        } else {
            // 如果字符串末尾没有连续序列，将剩余字符添加到结果中
            result.append(str.substring(startIndex));
        }

        return result.toString();
    }
}
