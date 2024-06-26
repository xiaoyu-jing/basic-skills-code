package com.tech.solution.leetcode;

/**
 * @author jing1560
 * @data 2024/3/19
 *
 * 算法：岛屿的最大面积
 *
 * 给你一个大小为 m x n 的二进制矩阵 grid 。
 * 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在 水平或者竖直的四个方向上 相邻。你可以假设 grid 的四个边缘都被 0（代表水）包围着。
 * 岛屿的面积是岛上值为 1 的单元格的数目。
 * 计算并返回 grid 中最大的岛屿面积。如果没有岛屿，则返回面积为 0 。
 *
 * 示例 1：
 * 输入：grid = [[0,0,1,0,0,0,0,1,0,0,0,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,1,1,0,1,0,0,0,0,0,0,0,0],[0,1,0,0,1,1,0,0,1,0,1,0,0],[0,1,0,0,1,1,0,0,1,1,1,0,0],[0,0,0,0,0,0,0,0,0,0,1,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,0,0,0,0,0,0,1,1,0,0,0,0]]
 * 输出：6
 * 解释：答案不应该是 11 ，因为岛屿只能包含水平或垂直这四个方向上的 1 。
 *
 * 示例 2：
 * 输入：grid = [[0,0,0,0,0,0,0,0]]
 * 输出：0
 *
 * 提示：
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 50
 * grid[i][j] 为 0 或 1
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

        int[][] grid1 = {{0,0,0,0,0,0,0,0}};
        int maxArea1 = maxAreaOfIsland(grid1);
        System.out.println("最大岛屿面积为：" + maxArea1);
    }

    private static int maxAreaOfIsland(int[][] grid) {
        int maxArea = 0;
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[i].length; j++){
                if(grid[i][j] == 1){
                    /**
                     * 中：grid[i][j]
                     * 上：grid[i-1][j]
                     * 下：grid[i+1][j]
                     * 左：grid[i][j-1]
                     * 右：grid[i][j+1]
                     */
                    int sumMaxArea = findMaxArea(grid, i, j);
                    maxArea = Math.max(maxArea, sumMaxArea);
                }
            }
        }
        return maxArea;
    }

    private static int findMaxArea(int[][] grid, int i, int j){
        // 第一块岛屿的面积初始为1
        int sumArea = 1;
        // 将统计过的陆地设置为 2，防止重复统计
        grid[i][j] = 2;
        // 上：grid[i-1][j]
        if((i - 1) > 0 && grid[i-1][j] == 1){
            sumArea += findMaxArea(grid, i-1, j);
        }
        // 下：grid[i+1][j]
        if((i + 1) < grid.length && grid[i+1][j] == 1){
            sumArea += findMaxArea(grid, i+1, j);
        }
        // 左：grid[i][j-1]
        if((j - 1) > 0 && grid[i][j-1] == 1){
            sumArea += findMaxArea(grid, i, j-1);
        }
        // 右：grid[i][j+1]
        if((j + 1) < grid[i].length && grid[i][j+1] == 1){
            sumArea += findMaxArea(grid, i, j+1);
        }
        return sumArea;
    }
}
