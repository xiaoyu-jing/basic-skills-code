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

        System.out.println(zformalConvertSolution2("PAYPALISHIRING", 3));
        System.out.println(zformalConvertSolution2("PAYPALISHIRING", 4));
        System.out.println(zformalConvertSolution2("A", 1));
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

    /**
     * 解法二：官方解法   利用二维矩阵模拟
     * @param s
     * @param numRows
     * @return
     */
    private static String zformalConvertSolution2(String s, int numRows){
        int n = s.length();
        if(numRows == 1 || numRows >= n){
            return s;
        }
        // 在矩阵上填写字符时，会向下填写 r 个字符，然后向右上继续填写 r−2 个字符，最后回到第一行，因此 Z 字形变换的周期 t=r+r−2=2r−2
        // 2*numRows−2 表示一个 Z 字形周期中有几个字符
        int cycleNum = 2 * numRows - 2;
        // 每个周期会占用矩阵上的 1+numRows−2= numRows−1 列
        // (n / cycleNum) 表示有多少个周期；(numRows - 1) 表示 一个周期有几列； 乘积表示所有周期共有几列
        //int column = (n / cycleNum) * (numRows - 1) + 1;
        // (n+t-1)/t * (r-1): 要把最后一个周期视作一个完整周期，所以要+t-1，可以当作是要让它向上取整而不是向下取整. "-1" 是因为要排除 n 正好被周期整除的时候
        int column = (n + cycleNum - 1) / cycleNum * (numRows - 1);
        // 二维数组存储字符串构造数据
        char[][] resultArr = new char[numRows][column];
        // 上下移动
        int upX = 0;
        // 右上移动
        int rightY = 0;
        for(int i = 0; i < n; i++){
            resultArr[upX][rightY] = s.charAt(i);
            // (numRows - 1) 表示 一个周期有几列；
            // i % cycleNum  表示 i 处于 一个周期中的第几列
            if(i % cycleNum < numRows - 1){
                upX++; // 向下移动
            } else {
                upX--;
                rightY++;  // 向右上移动
            }
        }

        StringBuilder builder = new StringBuilder();
        for(char[] rowValue : resultArr){
            for(char columnValue : rowValue){
                if(columnValue != 0){
                    builder.append(columnValue);
                }
            }
        }

        return builder.toString();
    }
}
