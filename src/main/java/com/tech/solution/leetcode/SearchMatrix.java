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

        System.out.println("是否存在 5 ： " + searchMatrixSolution2(matrix, 5));
        System.out.println("是否存在 5 ： " + searchMatrixSolution3(matrix, 5));

        int[][] matrix1 = {{1,4},{2,5}};
        System.out.println("是否存在 2 ： " + searchMatrixSolution2(matrix1, 2)); // 该case不正确

        int[][] matrix3 = {{1,4},{2,5}};
        System.out.println("是否存在 2 ： " + searchMatrixSolution3(matrix3, 2)); // 该case正确
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
        解法一：暴力解法(官方)
     */
    public static boolean searchMatrixSolution11(int[][] matrix, int target){
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[i].length; j++){
                if(matrix[i][j] == target){
                    return true;
                }
            }
        }
        return false;
    }

    /**
       解法二：二维数组的二分查找法 (存在部分通不过的用例)
       思路：先 从第一行 开始找最接近目标值的列，然后从 指定列上寻找目标值
     */
    public static boolean searchMatrixSolution2(int[][] matrix, int target){
        int[] columnMatrix = binarySearchByColumn(matrix, target);
        return binarySearchByRow(columnMatrix,target);
    }

    public static int[] binarySearchByColumn(int[][] matrix, int target){
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

    public static boolean binarySearchByRow(int[] columnMatrix, int target){
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

    /**
     * 解法三：官方解法 - 二维数组的二分查找法
     *         思路：一行一行的遍历，每一行都使用二分查找法
     * @param matrix
     * @param target
     * @return
     */
    public static boolean searchMatrixSolution3(int[][] matrix, int target){
        for(int[] row : matrix){
            int index = searchDataByRow(row, target);
            if(index >= 0){
                return true;
            }
        }
        return false;
    }

    public static int searchDataByRow(int[] row, int target){
        int left = 0, right = row.length - 1;
        while (left <= right){
            int mid = (right - left) / 2 + left;
            if(row[mid] < target){
                left = mid + 1;
            }else if(row[mid] > target){
                right = mid - 1;
            }else {
                return mid;
            }
        }
        return -1;
    }
}
