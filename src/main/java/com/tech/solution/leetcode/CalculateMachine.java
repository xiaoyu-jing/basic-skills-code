package com.tech.solution.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author jing1560
 * @data 2024/4/10
 *
 * 算法：计算器
 *
 * 给定一个包含正整数、加(+)、减(-)、乘(*)、除(/)的算数表达式(括号除外)，计算其结果。
 * 表达式仅包含非负整数，+， - ，*，/ 四种运算符和空格  。 整数除法仅保留整数部分。
 *
 * 示例 1:
 * 输入: "3+2*2"
 * 输出: 7
 *
 * 示例 2:
 * 输入: " 3/2 "
 * 输出: 1
 *
 * 示例 3:
 * 输入: " 3+5 / 2 "
 * 输出: 5
 */
public class CalculateMachine {

    public static void main(String[] args){
        System.out.println(calculate(" 5-3 / 2"));

        System.out.println(calculate(" 589-3 / 2"));

        System.out.println(calculate("3+2*2"));
    }

    public static int calculate(String s) {
        Deque<Integer> stack = new ArrayDeque<Integer>();
        // 前一个符号（假设字符串之前的默认符号为 +）
        char preSign = '+';
        int num = 0;
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            if (Character.isDigit(s.charAt(i))) {
                /**
                 * num * 10 的含义： 例如数字为 589
                 * 第一次循环：5 * 10 = 50  ---> 50 + 8 = 58
                 * 第一次循环：58 * 10 = 580  ---> 580 + 9 = 589
                 */
                num = num * 10 + s.charAt(i) - '0';  // s.charAt(i) - '0' 得到的是 对应的整数值
            }
            // i == n - 1  表示最后一位是数字时，依旧进行如下判断
            if (!Character.isDigit(s.charAt(i)) && s.charAt(i) != ' ' || i == n - 1) {
                switch (preSign) {
                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        // 如果前一个符号是“-”，表示负数，所以此处直接取负数
                        stack.push(-num);
                        break;
                    case '*':
                        stack.push(stack.pop() * num);
                        break;
                    case '/':
                        stack.push(stack.pop() / num);
                    default:
                }
                // 每遇到一个符号，前序需要 preSign 就要同时修改
                preSign = s.charAt(i);
                // 每遇到一个符号，num 就要清零
                num = 0;
            }
        }
        int ans = 0;
        // 栈中的所有值相加
        while (!stack.isEmpty()) {
            ans += stack.pop();
        }
        return ans;
    }

}
