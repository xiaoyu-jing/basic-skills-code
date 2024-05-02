package com.tech.solution.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jing1560
 * @data 2024/5/2
 *
 * 算法：Z 字形变换【字节 三面算法】
 *
 * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
 * 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
 *
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 *
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
 * 请你实现这个将字符串进行指定行数变换的函数：
 * string convert(string s, int numRows);
 *
 * 示例 1：
 * 输入：s = "PAYPALISHIRING", numRows = 3
 * 输出："PAHNAPLSIIGYIR"
 *
 * 示例 2：
 * 输入：s = "PAYPALISHIRING", numRows = 4
 * 输出："PINALSIGYAHRPI"
 * 解释：
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 *
 * 示例 3：
 * 输入：s = "A", numRows = 1
 * 输出："A"
 *
 * 提示：
 * 1 <= s.length <= 1000
 * s 由英文字母（小写和大写）、',' 和 '.' 组成
 * 1 <= numRows <= 1000
 */
public class ZFormalTransformation {

    public static void main(String[] args){
        System.out.println(zformalConvertSolution1("PAYPALISHIRING", 3));
        System.out.println(zformalConvertSolution1("PAYPALISHIRING", 4));
        System.out.println(zformalConvertSolution1("A", 1));
    }

    /**
     * 解法一：flag 解法
     *
     * 详细解法：https://leetcode.cn/problems/zigzag-conversion/solutions/21610/zzi-xing-bian-huan-by-jyd/
     * @return
     */
    private static String zformalConvertSolution1(String s, int numRows){
        if(numRows < 2){
            return s;
        }
        List<StringBuilder> res = new ArrayList<>();
        for(int i = 0; i < numRows; i++){
            res.add(new StringBuilder());
        }

        int i = 0;
        // 转向标识
        int flag = -1;
        for(char c : s.toCharArray()){
            res.get(i).append(c);
            if(i == 0 || i == numRows - 1){
                // 在达到 Z 字形转折点时，执行反向
                flag = -flag;
            }
            // 更新当前字符 c 对应的行索引
            i += flag;
        }

        StringBuilder builder = new StringBuilder();
        for(StringBuilder row : res){
            builder.append(row);
        }
        return builder.toString();
    }
}
