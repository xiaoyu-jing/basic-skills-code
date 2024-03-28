package com.tech.solution.leetcode;

/**
 * @author jing1560
 * @data 2024/3/28
 *
 * 算法：岛屿数量
 *
 * 方法1，该方法较笨重，是参考了"计算最大岛屿面积 算法"
 * 更好的方法可以查看 NumIsLandsSolution2.java
 */
public class NumIsLandsSolution1 {

    public static void main(String[] args){
        char[][] grid = {{'1','1','0','0','0'},
                         {'1','1','0','0','0'},
                         {'0','0','1','0','0'},
                         {'0','0','0','1','1'}};

        int maxArea = numIslands(grid);
        System.out.println("岛屿数量为：" + maxArea);

        char[][] grid1 = {{'1','1','1','1','0'},
                          {'1','1','0','1','0'},
                          {'1','1','0','0','0'},
                          {'0','0','0','0','0'}};

        int maxArea1 = numIslands(grid1);
        System.out.println("岛屿数量为：" + maxArea1);
    }

    public static int numIslands(char[][] grid) {
        int isLandsCount = 0;
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[i].length; j++){
                /**
                 中：grid[i][j]
                 上：grid[i-1][j]
                 下：grid[i+1][j]
                 左：grid[i][j-1]
                 右：grid[i][j+1]
                 */
                if(grid[i][j] == '1'){
                    int isLandsAreaSum = findIsLandsCount(grid,i,j);
                    if(isLandsAreaSum >= 1){
                        isLandsCount++;
                    }
                }
            }
        }
        return isLandsCount;
    }

    private static int findIsLandsCount(char[][] grid,int i, int j){
        //第一块岛屿位置初始化为1
        int sum = 1;
        //将统计过的陆地设置为2，防止重复统计
        grid[i][j] = '2';

        //上：grid[i-1][j]
        if(i > 0 && grid[i-1][j] == '1'){
            sum++;
            //该方法虽然是空执行，但是可以用于递归寻找下一个岛屿节点
            findIsLandsCount(grid, i-1, j);
        }
        //下：grid[i+1][j]
        if(i + 1 < grid.length && grid[i+1][j] == '1'){
            sum++;
            findIsLandsCount(grid, i+1, j);
        }
        //左：grid[i][j-1]
        if(j > 0 && grid[i][j-1] == '1'){
            sum++;
            findIsLandsCount(grid, i, j-1);
        }
        //右：grid[i][j+1]
        if(j + 1 < grid[i].length && grid[i][j+1] == '1'){
            sum++;
            findIsLandsCount(grid, i, j+1);
        }

        return sum;
    }
}
