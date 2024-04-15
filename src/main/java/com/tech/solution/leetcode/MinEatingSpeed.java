package com.tech.solution.leetcode;

import java.util.Arrays;

/**
 * @author jing1560
 * @data 2024/3/20
 *
 * 算法：LCR 073.爱吃香蕉的狒狒
 *
 * 狒狒喜欢吃香蕉。这里有 N 堆香蕉，第 i 堆中有 piles[i] 根香蕉。警卫已经离开了，将在 H 小时后回来。
 * 狒狒可以决定她吃香蕉的速度 K （单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉 K 根。如果这堆香蕉少于 K 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉，下一个小时才会开始吃另一堆的香蕉。
 * 狒狒喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。
 * 返回她可以在 H 小时内吃掉所有香蕉的最小速度 K（K 为整数）。
 *
 * 示例 1：
 * 输入: piles = [3,6,7,11], H = 8
 * 输出: 4
 *
 * 示例 2：
 * 输入: piles = [30,11,23,4,20], H = 5
 * 输出: 30
 *
 * 示例 3：
 * 输入: piles = [30,11,23,4,20], H = 6
 * 输出: 23
 */
public class MinEatingSpeed {

    public static void main(String[] args){
        System.out.println("示例1 - 最小速度为：" + minEatingSpeedTest1(new int[]{3,6,7,11},8));
        System.out.println("示例2 - 最小速度为：" + minEatingSpeedTest1(new int[]{30,11,23,4,20},5));
        System.out.println("示例3 - 最小速度为：" + minEatingSpeedTest1(new int[]{30,11,23,4,20},6));
    }

    private static int minEatingSpeedTest1(int[] piles, int h){
        //最小速度的初始值从 吃完所有香蕉平均值开始，可以缩小最小速度的递增起始值，因为吃香蕉的速度肯定会比平均速度大，提高一下初始值（之前是从1开始递增的）
        int minSpeed = Arrays.stream(piles).sum() / h;
        while (true){
            //吃完所有堆香蕉的总用时(每次清0)
            int sumHeapTime = 0;
            for(int i = 0; i < piles.length; i++){
                //向上取整（下面两个除法运算 等价于 (pile + minSpeed - 1) / minSpeed
                int shang = piles[i] / minSpeed;
                if((piles[i] % minSpeed) > 0){
                    shang += 1;
                }
                sumHeapTime += shang;
            }
            //吃香蕉的速度，当第一次等于H的时候就退出，表示 速度 最小
            if(sumHeapTime == h){
                return minSpeed;
            }
            //不符合条件的情况下，速度+1
            minSpeed++;
        }
    }

}
