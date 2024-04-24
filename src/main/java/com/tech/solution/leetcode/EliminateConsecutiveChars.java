package com.tech.solution.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
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

    /**
     * 最佳解法 查看 【解法 3】
     * @param args
     */
    public static void main(String[] args){
        String[] testCases = {
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
        }

    }

    /**
     * 解法 1：参考 LeetCode《最长回文子串》获取 "最大连续重复子串的长度" 和 "最大连续重复子串的起始索引"
     * @param str
     * @return
     */
    private static String eliminateConsecutiveChars1(String str){
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
                    // 此行为优化写法，可减少遍历的次数，提高性能 ； 配合 (j - i + 1) >= maxLength 一起使用，必须是 ">=" ；
                    // 不加 break 这个 else 块， ">=" 需要改为 ">"
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
            replaceStr = eliminateConsecutiveChars1(replaceStr);
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

    /**
     * 解法 2：优化写法   （一层 for 循环）
     * @param str
     * @return
     */
    private static String eliminateConsecutiveChars2(String str){
        // 最大连续重复子串的长度
        int maxLength = 0;
        // 最大连续重复子串的起始索引
        int begin = 0;
        char[] strArr = str.toCharArray();
        // 用于计数的长度(由于要比较 后者 和 当前值 的大小，默认已经有一位长度了，所以初始值从 1 开始)
        int countLength = 1;
        // bbrggggrrbr
        for(int i = 0; i < strArr.length - 1; i++){
            if(strArr[i] == strArr[i+1]){
                // 后者 和 当前值 相同时，先给长度 + 1
                countLength++;
                // 计数长度 大于 最大长度时，给 起始索引 和 最大长度 赋值
                if(countLength > maxLength){
                    // 第一个"+1"：因为是 后者 和 当前值 比较，所以 countLength 记录的最大位置 要比 i 多 1，（i - countLength）相当于多减了 1，还需要加回来
                    // 第二个"+1"：i 是 索引，比实际长度小 1，countLength 是 长度，用 索引 - 长度 还应该再 + 1，才是真实的索引位
                    begin = i - countLength + 1 + 1;
                    maxLength = countLength;
                }
            } else {
                // 如果前后两个值不相同，则将计数器的值还原
                countLength = 1;
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
            replaceStr = eliminateConsecutiveChars2(replaceStr);
        }
        // 不存在连续重复字符时，直接返回剔除重复后的子串
        return replaceStr;
    }

    /**
     * 解法 3：最佳解法  （一层 for 循环）
     * @param str
     * @return
     */
    private static String eliminateConsecutiveChars3(String str){
        // 最大连续重复子串的长度
        int maxLength = 0;
        // 最大连续重复子串的起始索引
        int begin = 0;
        char[] strArr = str.toCharArray();
        // 用于计数的长度(由于要比较 前者 和 当前值 的大小，默认已经有一位长度了，所以初始值从 1 开始)
        int countLength = 1;
        // 示例：bbrggggrrbr
        for(int i = 1; i < strArr.length; i++){
            if(strArr[i - 1] == strArr[i]){
                // 前者 和 当前值 相同时，先给长度 + 1
                countLength++;
                // 计数长度 大于 最大长度时，给 起始索引 和 最大长度 赋值
                if(countLength > maxLength){
                    // "+1"：i 是 索引，countLength 是 长度，i 是 countLength 的最后边界值，用 索引 - 长度 再 + 1，才是连续重复子串的起始索引位
                    begin = i - countLength + 1;
                    maxLength = countLength;
                }
            } else {
                // 如果前后两个值不相同，则将计数器的值还原
                countLength = 1;
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
                // 只要存在连续且重复的字符 就设置为 true
                flag = true;
                break;
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
     * 解法 4：通义千问 的解法 + 人工修改后 才正确
     * @param s
     * @return
     */
    public static String eliminateConsecutiveChars4(String s) {
        StringBuilder sb = new StringBuilder(s);
        while (true) {
            // 最大连续重复子串的长度
            int maxLength = 0;
            // 最大连续重复子串的起始索引
            int begin = 0;
            // 用于计数的长度(由于要比较 前者 和 当前值 的大小，默认已经有一位长度了，所以初始值从 1 开始)
            int countLength = 1;

            // 找到最左边最长的连续子串
            for (int i = 1; i < sb.length(); i++) {
                if (sb.charAt(i) == sb.charAt(i - 1)) {
                    // 前者 和 当前值 相同时，先给长度 + 1
                    countLength++;
                    // 计数长度 大于 最大长度时，给 起始索引 和 最大长度 赋值
                    if(countLength > maxLength){
                        // "+1"：i 是 索引，countLength 是 长度，i 是 countLength 的最后边界值，用 索引 - 长度 再 + 1，才是连续重复子串的起始索引位
                        begin = i - countLength + 1;
                        maxLength = countLength;
                    }
                } else {
                    // 如果前后两个值不相同，则将计数器的值还原
                    countLength = 1;
                }
            }

            // Eliminate the longest consecutive substring
            sb.delete(begin, begin + maxLength);

            // 判断剩余字符串中是否有连续重复字符，如果有设置 flag 为 true，进行下一轮的判断
            boolean flag = false;
            for(int k = 1; k < sb.toString().length(); k++){
                if(sb.charAt(k) == sb.charAt(k - 1)){
                    // 只要存在连续且重复的字符 就设置为 true
                    flag = true;
                    break;
                }
            }

            if(!flag){
                // 不存在连续重复字符时，跳出循环
                break;
            }
        }

        return sb.toString();
    }

}
