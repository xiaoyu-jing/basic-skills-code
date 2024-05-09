package com.tech.solution.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jing1560
 * @data 2024/5/9
 *
 * 算法：螺旋矩阵
 *
 * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 *
 * 示例 1：
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 *
 * 示例 2：
 * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 *
 * 提示：
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 10
 * -100 <= matrix[i][j] <= 100
 */
public class SpiralOrder {

    public static void main(String[] args){

    }

    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        int m = matrix.length, n = matrix[0].length;
        int left = 0, right = n - 1, top = 0, bottom = m - 1;
        // 每一次 while 循环都是一整圈
        while( left <= right && top <= bottom){
            // 从左到右
            for(int i = left; i <= right; i++){
                res.add(matrix[top][i]);
            }
            top++;
            // 从上到下
            for(int i = top; i <= bottom; i++){
                res.add(matrix[i][right]);
            }
            right--;
            // 从右到左
            if(top <= bottom){
                for(int i = right; i >= left; i--){
                    res.add(matrix[bottom][i]);
                }
            }
            bottom--;
            // 从下到上
            if(left <= right){
                for(int i = bottom; i >= top; i--){
                    res.add(matrix[i][left]);
                }
            }
            left++;
        }
        return res;
    }
}
