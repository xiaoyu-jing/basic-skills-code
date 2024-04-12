package com.tech.solution.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author jing1560
 * @data 2024/4/12
 *
 * 算法：去除重复字母。  （同另一道算法题 【不同字符的最小子序列】）
 *
 * 给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
 * s 由小写英文字母组成
 *
 * 示例 1：
 * 输入：s = "bcabc"
 * 输出："abc"
 *
 * 示例 2：
 * 输入：s = "cbacdcbc"
 * 输出："acdb"
 *
 */
public class RemoveDuplicateLetters {

    public static void main(String[] args){
        System.out.println("解法二：" + removeDuplicateLettersSolution2("bcabc"));
        System.out.println("解法二：" + removeDuplicateLettersSolution2("cbacdcbc"));

        System.out.println("解法三：" + removeDuplicateLettersSolution3("bcabc"));
        System.out.println("解法三：" + removeDuplicateLettersSolution3("cbacdcbc"));
    }

    /**
     * 解法一：暴力解法
     * @param s
     * @return
     */
    private static String removeDuplicateLettersSolution1(String s){
        return "";
    }

    /**
     * 解法二：单调栈   (这种解法不推荐， 因为数组用的都是 26 的长度，传值的时候，需要给每个值都 -'a' , 容易记不住， 推荐 解法三)
     *
     * 详细算法分析：https://leetcode.cn/problems/remove-duplicate-letters/solutions/417758/you-qian-ru-shen-dan-diao-zhan-si-lu-qu-chu-zhong-/
     * @return
     */
    private static String removeDuplicateLettersSolution2(String s){
        // 数组长度为 26，表示 26个字母， 每次存值时，都需要 -'a'，才能得到具体的差值数字
        int[] count = new int[26];
        // 将每一个字符的个数，存在数组中
        for(int i = 0; i < s.length(); i++){
            count[s.charAt(i) - 'a']++;
        }
        Deque<Character> deque = new ArrayDeque<>();
        // 数组长度为 26，表示 26个字母， 每次存值时，都需要 -'a'，才能得到具体的差值数字
        boolean[] inStack = new boolean[26];
        for(int i = 0; i < s.length(); i++){
            // 每循环一次，就给当前字符的个数减一
            int c = s.charAt(i) - 'a';
            count[c]--;

            // 如果栈中存在这个 字符，则跳过
            if(inStack[c]){
                continue;
            }

            while (!deque.isEmpty() && deque.peekLast() > s.charAt(i)) {
                // 判断栈顶元素的个数是否已经唯一，若之后不存在栈顶元素了，则停止 pop
                if(count[deque.peekLast() - 'a'] == 0){
                    break;
                }
                // 若之后还有，则可以 pop
                inStack[deque.pollLast() - 'a'] = false;
            }

            deque.offerLast(s.charAt(i));
            inStack[c] = true;
        }

        StringBuilder builder = new StringBuilder();
        while (!deque.isEmpty()) {
            builder.append(deque.pollFirst());
        }

        return builder.toString();
    }

    /**
     * 解法三：单调栈 -- ASCII 字符  （推荐做法）
     *
     * 详细算法分析：https://leetcode.cn/problems/remove-duplicate-letters/solutions/417758/you-qian-ru-shen-dan-diao-zhan-si-lu-qu-chu-zhong-/
     * @return
     */
    private static String removeDuplicateLettersSolution3(String s){
        // 维护一个计数器记录字符串中字符的数量
        // 因为输入为 ASCII 字符，大小 256 够用了
        int[] count = new int[256];
        for(int i = 0; i < s.length(); i++){
            count[s.charAt(i)]++;
        }
        Deque<Character> deque = new ArrayDeque<>();
        boolean[] inStack = new boolean[256];
        for(char c : s.toCharArray()){
            // 每遍历过一个字符，都将对应的计数减一
            count[c]--;
            if(inStack[c]){
                continue;
            }
            while (!deque.isEmpty() && deque.peekLast() > c){
                // 判断栈顶元素的个数是否已经唯一，若之后不存在栈顶元素了，则停止 pop
                if(count[deque.peekLast()] == 0){
                    break;
                }
                // 若之后还有，则可以 pop
                inStack[deque.pollLast()] = false;
            }
            deque.offerLast(c);
            inStack[c] = true;
        }
        StringBuilder builder = new StringBuilder();
        while (!deque.isEmpty()) {
            builder.append(deque.pollFirst());
        }
        return builder.toString();
    }

}
