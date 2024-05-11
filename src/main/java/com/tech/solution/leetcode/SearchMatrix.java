package com.tech.solution.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jing1560
 * @data 2024/5/11
 *
 * 算法：搜索二位矩阵 II
 *
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
 *    1）每行的元素从左到右升序排列。
 *    2）每列的元素从上到下升序排列。
 *
 * 示例 1：
 * 输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
 * 输出：true
 *
 * 示例 2：
 * 输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 20
 * 输出：false
 *
 * 提示：
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= n, m <= 300
 * -10^9 <= matrix[i][j] <= 10^9
 * 每行的所有元素从左到右升序排列
 * 每列的所有元素从上到下升序排列
 * -10^9 <= target <= 10^9
 */
public class SearchMatrix {

    public static void main(String[] args){
        int[][] matrix = {{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}};
        System.out.println("是否存在 5 ： " + searchMatrixSolution1(matrix, 5));
    }

    /**
       解法一：暴力解法
     */
    public static boolean searchMatrixSolution1(int[][] matrix, int target){
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[i].length; j++){
                list.add(matrix[i][j]);
            }
        }
        if(list.contains(target)){
            return true;
        }
        return false;
    }

    /**
       解法二：二维数组的二分查找法
     */
    public boolean searchMatrixSolution2(int[][] matrix, int target){
        int[] columnMatrix = binarySearchByColumn(matrix, target);
        return binarySearchByRow(columnMatrix,target);
    }

    public int[] binarySearchByColumn(int[][] matrix, int target){
        int left = 0, right = matrix[0].length - 1;
        while(left < right){
            int mid = (right - left) / 2 + left;
            if(matrix[0][mid] < target){
                left = mid + 1;
            } else if(matrix[0][mid] > target){
                right = mid - 1;
            } else {
                left = mid;
                break;
            }
        }
        int[] matrix_column = new int[matrix.length];
        for(int i = 0; i < matrix.length; i++){
            matrix_column[i] = matrix[i][left];
        }
        return matrix_column;
    }

    public boolean binarySearchByRow(int[] columnMatrix, int target){
        int left = 0, right = columnMatrix.length - 1;
        while(left <= right){
            int mid = (right - left) / 2 + left;
            if(columnMatrix[mid] < target){
                left = mid + 1;
            } else if(columnMatrix[mid] > target){
                right = mid - 1;
            } else {
                return true;
            }
        }
        return false;
    }
}
