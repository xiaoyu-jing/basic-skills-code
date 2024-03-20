package com.tech.solution.leetcode;

import java.util.Arrays;

/**
 * @author jing1560
 * @data 2024/3/20
 * LCR 073.爱吃香蕉的狒狒
 */
public class MinEatingSpeed {
    /**
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
     *
     * 请输出最小速度
     */

    public static void main(String[] args){
        System.out.println("示例1 - 最小速度为：" + minEatingSpeedTest1(new int[]{3,6,7,11},8));
        System.out.println("示例2 - 最小速度为：" + minEatingSpeedTest1(new int[]{30,11,23,4,20},5));
        System.out.println("示例3 - 最小速度为：" + minEatingSpeedTest1(new int[]{30,11,23,4,20},6));
    }

    /**
     * 方法1
     * @param piles
     * @param h
     * @return
     */
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

    /**
     * 方法2
     * @param piles
     * @param h
     * @return
     */
    private static int minEatingSpeedTest2(int[] piles, int h){

        return 0;
    }
}
