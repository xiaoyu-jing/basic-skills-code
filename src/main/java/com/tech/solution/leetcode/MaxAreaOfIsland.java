package com.tech.solution.leetcode;

/**
 * @author jing1560
 * @data 2024/3/19
 * 岛屿最大面积
 */
public class MaxAreaOfIsland {

    public static void main(String[] args){
        int[][] grid = {{0,0,1,0,0,0,0,1,0,0,0,0,0},
                        {0,0,0,0,0,0,0,1,1,1,0,0,0},
                        {0,1,1,0,1,0,0,0,0,0,0,0,0},
                        {0,1,0,0,1,1,0,0,1,0,1,0,0},
                        {0,1,0,0,1,1,0,0,1,1,1,0,0},
                        {0,0,0,0,0,0,0,0,0,0,1,0,0},
                        {0,0,0,0,0,0,0,1,1,1,0,0,0},
                        {0,0,0,0,0,0,0,1,1,0,0,0,0}};
        int maxArea = maxAreaOfIsland(grid);
        System.out.println("最大岛屿面积为：" + maxArea);
    }

    public static int maxAreaOfIsland(int[][] grid){
        int maxArea = 0;
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[i].length; j++){
                /**
                 * 中：grid[i][j]
                 * 上：grid[i-1][j]
                 * 下：grid[i+1][j]
                 * 左：grid[i][j-1]
                 * 右：grid[i][j+1]
                 */
                if(grid[i][j] == 1){
                    int sumMaxArea = findMaxArea(grid,i,j);
                    maxArea = Math.max(sumMaxArea,maxArea);
                }
            }
        }
        return maxArea;
    }

    private static int findMaxArea(int[][] grid, int i, int j) {
        //第一块岛屿
        int sumMaxArea = 1;
        //将统计过的陆地设置为2，防止重复统计
        grid[i][j] = 2;
        //上
        if(i - 1 > 0 && grid[i-1][j] == 1){
            sumMaxArea += findMaxArea(grid, i-1,j);
        }
        //下
        if(i + 1 < grid.length && grid[i+1][j] == 1){
            sumMaxArea += findMaxArea(grid, i+1,j);
        }
        //左
        if(j - 1 > 0 && grid[i][j-1] == 1){
            sumMaxArea += findMaxArea(grid, i,j-1);
        }
        //右
        if(j + 1 < grid[i].length && grid[i][j+1] == 1){
            sumMaxArea += findMaxArea(grid, i,j+1);
        }
        return sumMaxArea;
    }
}
