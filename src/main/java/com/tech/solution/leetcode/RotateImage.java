package com.tech.solution.leetcode;

import java.util.Arrays;

/**
 * @author jing1560
 * @data 2024/5/9
 *
 * 算法：旋转图像
 *
 * 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
 * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
 *
 * 示例 1：
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[[7,4,1],[8,5,2],[9,6,3]]
 *
 * 示例 2：
 * 输入：matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
 * 输出：[[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
 *
 * 提示：
 * n == matrix.length == matrix[i].length
 * 1 <= n <= 20
 * -1000 <= matrix[i][j] <= 1000
 */
public class RotateImage {

    public static void main(String[] args){
        int[][] matrix = {{1,2,3}, {4,5,6},{7,8,9}};
        rotateImageSolution1(matrix);
        printImage(matrix);
    }

    /**
     * 解法一：使用辅助数组
     *       规律：对于矩阵中第 i 行的第 j 个元素，在旋转后，它出现在倒数第 i 列的第 j 个位置。
     */
    private static void rotateImageSolution1(int[][] matrix){
        int n = matrix.length;
        int[][] matrix_new = new int[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                matrix_new[j][n - i - 1] = matrix[i][j];
            }
        }
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                matrix[i][j] = matrix_new[i][j];
            }
        }
    }

    private static void printImage(int[][] matrix){
        for(int i = 0; i < matrix.length; i++){
            System.out.println(Arrays.toString(matrix[i]));
        }
    }
}
