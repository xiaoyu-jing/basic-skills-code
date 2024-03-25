package com.tech.solution.leetcode;

import java.util.*;

/**
 * @author jing1560
 * @data 2024/3/25
 *
 * 算法：【矩阵置零】
 *
 * 给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。
 *
 * 示例 1：
 * 输入：matrix = [[1,1,1],[1,0,1],[1,1,1]]
 * 输出：[[1,0,1],[0,0,0],[1,0,1]]
 *
 * 示例 2：
 * 输入：matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
 * 输出：[[0,0,0,0],[0,4,5,0],[0,3,1,0]]
 */
public class MatrixSetZeroes {

    public static void main(String[] args){
        System.out.println("-------示例1-------");
        int[][] matrix = {{1,1,1}, {1,0,1}, {1,1,1}};
        System.out.println("原矩阵：");
        for(int[] value : matrix){
            System.out.println(Arrays.toString(value));
        }
        //设置 零
        setZeroes(matrix);
        System.out.println("新矩阵：");
        for(int[] value : matrix){
            System.out.println(Arrays.toString(value));
        }

        System.out.println("-------示例2-------");
        int[][] matrix1 = {{0,1,2,0},{3,4,5,2},{1,3,1,5}};
        System.out.println("原矩阵：");
        for(int[] value : matrix1){
            System.out.println(Arrays.toString(value));
        }
        //设置 零
        setZeroes(matrix1);
        System.out.println("新矩阵：");
        for(int[] value : matrix1){
            System.out.println(Arrays.toString(value));
        }
    }

    private static void setZeroes(int[][] matrix) {
        List<String> list = new ArrayList<>();
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[i].length; j++){
                if(matrix[i][j] == 0){
                    findAndSetZeroes(matrix,i,j,list);
                }
            }
        }
        for(String str : list){
            String[] split = str.split("-");
            Integer key = Integer.valueOf(split[0]);
            Integer value = Integer.valueOf(split[1]);
            matrix[key][value] = 0;
        }
    }

    private static void findAndSetZeroes(int[][] matrix,int i,int j,List<String> list){
        int row = i;
        int column = j;
        //上  matrix[i-1][j]
        while ((i - 1) >= 0){
            list.add((i-1) + "-" + j);
            i--;
        }
        i = row;
        //下  matrix[i+1][j]
        while ((i + 1) < matrix.length){
            list.add((i+1) + "-" + j);
            i++;
        }
        i = row;
        //左  matrix[i][j-1]
        while ((j - 1) >= 0){
            list.add(i + "-" + (j-1));
            j--;
        }
        j = column;
        //右  matrix[i][j+1]
        while ((j + 1) < matrix[i].length){
            list.add(i + "-" + (j+1));
            j++;
        }
    }
}
