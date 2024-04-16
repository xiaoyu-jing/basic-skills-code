package com.tech.solution.leetcode;

/**
 * @author jing1560
 * @data 2024/3/28
 *
 * 算法：岛屿数量  【方法2 - 最优】
 *
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * 此外，你可以假设该网格的四条边均被水包围。
 *
 * 示例 1：
 * 输入：grid = [
 *   ["1","1","1","1","0"],
 *   ["1","1","0","1","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","0","0","0"]
 * ]
 * 输出：1
 *
 * 示例 2：
 * 输入：grid = [
 *   ["1","1","0","0","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","1","0","0"],
 *   ["0","0","0","1","1"]
 * ]
 * 输出：3
 */
public class NumIsLandsSolution2 {

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
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int nums = 0;
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[i].length; j++){
                if(grid[i][j] == '0' || visited[i][j]){
                    continue;
                }
                ++nums;
                dfs(grid, i, j, visited);
            }
        }
        return nums;
    }

    private static void dfs(char[][] grid, int i, int j, boolean[][] visited){
        // 6个条件
        if(i < 0 || j < 0 || i > grid.length - 1 || j > grid[i].length - 1 || grid[i][j] == '0' || visited[i][j]){
            return;
        }
        visited[i][j] = true;
        //上 (i-1,j)
        dfs(grid, i-1, j, visited);
        //下 (i+1,j)
        dfs(grid, i+1, j, visited);
        //左 (i,j-1)
        dfs(grid, i, j-1, visited);
        //右 (i,j+1)
        dfs(grid, i, j+1, visited);
    }

}
