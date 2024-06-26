package com.tech.solution.leetcode;

/**
 * @author jing1560
 * @data 2024/3/29
 *
 * 算法：搜索二维矩阵 - 二分查找 - 二维数组
 *
 * 给你一个满足下述两条属性的 m x n 整数矩阵：
 *  1）每行中的整数从左到右按非严格递增顺序排列。
 *  2）每行的第一个整数大于前一行的最后一个整数。
 * 给你一个整数 target ，如果 target 在矩阵中，返回 true ；否则，返回 false 。
 *
 * 示例 1：
 * 输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
 * 输出：true
 *
 * 示例 2：
 * 输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
 * 输出：false
 */
public class BinarySelectMultiArr {

    public static void main(String[] args){
        int[][] matrix = {{1,3,5,7},
                          {10,11,16,20},
                          {23,30,34,60}};

        System.out.println("一次 二分查找 3 ：" + searchMultiArr(matrix,3));
        System.out.println("两次 二分查找 3 ：" + searchMultiArr2(matrix,3));

    }

    // 方法一： 一次 二分查找

    private static boolean searchMultiArr(int[][] matrix, int target){
        int m = matrix.length, n = matrix[0].length;
        int low = 0, high = m * n - 1;
        while (low <= high) {
            int mid = (high - low) / 2 + low;
            int x = matrix[mid / n][mid % n];
            if(x < target){
                low = mid + 1;
            } else if (x > target){
                high = mid - 1;
            } else {
                return true;
            }
        }
        return false;
    }

    // 方法二： 两次 二分查找

    private static boolean searchMultiArr2(int[][] matrix, int target){
        //从二维数组的 列 先查询
        int rowIndex = searchMultiArrByColumn(matrix,target);
        if(rowIndex < 0){
            return false;
        }
        //再从二维数组的 行 查询
        return searchMultiArrByRow(matrix[rowIndex],target);
    }

    /**
     * 搜索列，找到行的索引值
     * @param matrix
     * @param target
     * @return
     */
    private static int searchMultiArrByColumn(int[][] matrix, int target){
        int low = -1, high = matrix.length - 1;
        while (low < high){
            int mid = (high - low + 1) / 2 + low;
            if(matrix[mid][0] <= target){
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    /**
     * 搜索行，找到目标值
     * @param row
     * @param target
     * @return
     */
    private static boolean searchMultiArrByRow(int[] row, int target){
        int low = 0, high = row.length - 1;
        while (low <= high) {
            int mid = (high - low) / 2 + low;
            if(row[mid] == target){
                return true;
            } else if(row[mid] < target){
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return false;
    }

}
