package com.tech.solution.leetcode;

/**
 * @author jing1560
 * @data 2024/3/28
 *
 * 算法：岛屿数量
 *
 *  方法2
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
