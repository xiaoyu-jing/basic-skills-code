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
 * 1. 优先消除最长的情况    【这个条件很重要，字节面试官现场要求注意这个条件！！！！】
 * 2. 长度一样，优先消除左侧
 * 3. 不强要求复杂度
 */
public class EliminateConsecutiveChars {

    public static void main(String[] args){
        /*String[] testCases = {
          "rgb",
          "rggb",
          "rggggb",
          "rggggrrb",
          "bbrggggrrb",
          "bbrggggrrbr",
          "abbaca"
        };
        for(String testCase : testCases){
            System.out.println(testCase + " => " + eliminateConsecutiveChars3(testCase));
        }*/

        System.out.println("bbrggggrrbr" + " => " + eliminateConsecutiveChars4("bbrggggrrbr"));

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

    private static String eliminateConsecutiveChars2(String input){
        StringBuilder result = new StringBuilder();
        int maxCount = 1; // 最长连续字符序列的计数
        int currentCount = 1; // 当前连续字符序列的计数

        for (int i = 1; i < input.length(); i++) {
            if (input.charAt(i) == input.charAt(i - 1)) {
                // 当前字符与前一个字符相同，增加连续计数
                currentCount++;
                if (currentCount > maxCount) {
                    // 发现更长的连续序列，更新最大值
                    maxCount = currentCount;
                }
            } else {
                // 当前字符与前一个字符不同，处理之前的连续序列
                if (maxCount > 1) {
                    // 如果连续序列长度大于1，则只保留一个字符
                    result.append(input.charAt(i - maxCount));
                } else {
                    // 否则，将字符添加到结果中
                    result.append(input.charAt(i - 1));
                }
                // 重置计数
                maxCount = 1;
                currentCount = 1;
            }
        }

        // 处理字符串末尾的连续序列
        if (maxCount > 1) {
            result.append(input.charAt(input.length() - maxCount));
        } else {
            // 如果字符串末尾没有连续序列，将最后一个字符添加到结果中
            result.append(input.charAt(input.length() - 1));
        }

        return result.toString();
    }

    /**
     *
     * @param str
     * @return
     */
    private static String eliminateConsecutiveChars3(String str){
        // 最大连续重复子串的长度
        int maxLength = 0;
        // 最大连续重复子串的起始索引
        int begin = 0;
        char[] strArr = str.toCharArray();
        // 因为判断的是 (j - i + 1) > maxLength，所以 最外层循环比内层循环 少1即可
        for(int i = 0; i < str.length() - 1; i++){
            for(int j = i + 1; j < str.length(); j++){
                // 获取最大长度 且 指定区间的子串全为重复字符 时，记录起始索引 和 子串的最大长度
                if((j - i + 1) >= maxLength && validSameChar(strArr, i, j)){
                    begin = i;
                    maxLength = j - i + 1;
                } else {
                    break;
                }
            }
        }
        String longStr = str.substring(begin, begin + maxLength);
        // 剔除最大重复子串
        String replaceStr = str.replaceAll(longStr, "");
        char[] newStrArr = replaceStr.toCharArray();
        // 判断剩余字符串中是否有连续重复字符，如果有设置 flag 为 true，进行下一轮的判断
        boolean flag = false;
        for(int k = 1; k < newStrArr.length; k++){
            if(newStrArr[k] == newStrArr[k - 1]){
                flag = true;
            }
        }

        if(flag){
            // 存在连续重复字符时，继续进行判断
            replaceStr = eliminateConsecutiveChars3(replaceStr);
        }
        // 不存在连续重复字符时，直接返回剔除重复后的子串
        return replaceStr;
    }

    /**
     * 判断指定区间中是否全为重复字符
     * @param strArr
     * @param left
     * @param right
     * @return
     */
    private static boolean validSameChar(char[] strArr, int left, int right){
        while (left < right){
            if(strArr[left] != strArr[left + 1]){
                return false;
            }
            left++;
        }
        return true;
    }

    private static String eliminateConsecutiveChars4(String str){
        // 最大连续重复子串的长度
        int maxLength = 1;
        // 最大连续重复子串的起始索引
        int begin = 0;
        char[] strArr = str.toCharArray();
        // 因为判断的是 (j - i + 1) > maxLength，所以 最外层循环比内层循环 少1即可
        for(int i = 0; i < strArr.length; i++){
            if(i == begin){
                if(strArr[i+1] == strArr[i]){
                    maxLength++;
                    begin = begin + maxLength;
                } else {
                    maxLength = 1;
                }
            }
        }
        String longStr = str.substring(begin, begin + maxLength);
        // 剔除最大重复子串
        String replaceStr = str.replaceAll(longStr, "");
        char[] newStrArr = replaceStr.toCharArray();
        // 判断剩余字符串中是否有连续重复字符，如果有设置 flag 为 true，进行下一轮的判断
        boolean flag = false;
        for(int k = 1; k < newStrArr.length; k++){
            if(newStrArr[k] == newStrArr[k - 1]){
                flag = true;
            }
        }

        if(flag){
            // 存在连续重复字符时，继续进行判断
            replaceStr = eliminateConsecutiveChars3(replaceStr);
        }
        // 不存在连续重复字符时，直接返回剔除重复后的子串
        return replaceStr;
    }

}
