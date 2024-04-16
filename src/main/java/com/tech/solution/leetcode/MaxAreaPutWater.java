package com.tech.solution.leetcode;

/**
 * @author jing1560
 * @data 2024/3/22
 *
 * 算法：盛最多水的容器
 *
 * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * 返回容器可以储存的最大水量。
 * 说明：你不能倾斜容器。
 *
 * 示例 1：
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 * 解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
 *
 * 示例 2：
 * 输入：height = [1,1]
 * 输出：1
 */
public class MaxAreaPutWater {

    public static void main(String[] args){
        System.out.println("解法1：双层循环 -- 最大容量为：" + maxAreaSolution1(new int[]{1,8,6,2,5,4,8,3,7}));

        System.out.println("解法2：伪双指针（双层for循环变体） -- 最大容量为：" + maxAreaSolution2(new int[]{1,1}));

        //双指针
        System.out.println("解法3：双指针 -- 最大容量为：" + maxAreaSolution3(new int[]{1,8,6,2,5,4,8,3,7}));
        System.out.println("解法3：双指针 -- 最大容量为：" + maxAreaSolution3(new int[]{1,1}));
    }

    /**
     * 解法一：双层循环暴力求解
     * @param height
     * @return
     */
    public static int maxAreaSolution1(int[] height){
        if(height.length <= 1){
            return 0;
        }
        int maxArea = 0;
        for(int i = 0; i < height.length; i++){
            for(int j = i+1; j < height.length; j++){
                int tempHeight = Math.min(height[i], height[j]);
                int length = j - i;
                maxArea = Math.max(maxArea, length * tempHeight);
            }
        }
        return maxArea;
    }

    /**
     解法二：伪双指针（其实还是双层for循环的变体）
     */
    public static int maxAreaSolution2(int[] height){
        if(height.length <= 1){
            return 0;
        }
        int left = 0;
        int right = 1;
        int maxArea = 0;
        while(right < height.length){
            if(left == height.length){
                break;
            }
            int tempArea = Math.min(height[left], height[right]) * (right - left);
            maxArea = Math.max(maxArea,tempArea);

            if(right == height.length - 1){
                left++;
                right = left + 1;
            } else {
                right++;
            }
        }
        return maxArea;
    }

    /**
     解法三：双指针
     */
    public static int maxAreaSolution3(int[] height){
        //在初始时，左右指针分别指向数组的左右两端
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;
        while(left < right){
            //左右指针的最小值 * 坐标长度
            int tempArea = Math.min(height[left],height[right]) * (right - left);
            maxArea = Math.max(maxArea,tempArea);

            if(height[left] <= height[right]){
                //移动左指针
                left++;
            } else {
                //移动右指针
                right--;
            }
        }
        return maxArea;
    }

}
