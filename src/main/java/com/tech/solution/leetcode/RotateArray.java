package com.tech.solution.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author jing1560
 * @data 2024/4/12
 *
 * 算法：轮转数组
 *
 * 示例 1:
 * 输入: nums = [1,2,3,4,5,6,7], k = 3
 * 输出: [5,6,7,1,2,3,4]
 * 解释:
 * 向右轮转 1 步: [7,1,2,3,4,5,6]
 * 向右轮转 2 步: [6,7,1,2,3,4,5]
 * 向右轮转 3 步: [5,6,7,1,2,3,4]
 *
 * 示例 2:
 * 输入：nums = [-1,-100,3,99], k = 2
 * 输出：[3,99,-1,-100]
 * 解释:
 * 向右轮转 1 步: [99,-1,-100,3]
 * 向右轮转 2 步: [3,99,-1,-100]
 */
public class RotateArray {

    public static void main(String[] args) {
        // 循环移位
//        cycleMovePosition();

        // 按位与 移位
//        cycleMovePositionByFIFO();

        System.out.println("轮转数组为：" + Arrays.toString(rotateArray(new int[]{1,2,3,4,5,6,7}, 3)));

        System.out.println("轮转数组为：" + Arrays.toString(rotateArray(new int[]{-1,-100,3,99}, 2)));
    }

    /**
     * 循环移位：JAVA中会使用模运算（%）来实现这种循环移位。 不建议使用 【按位与】进行循环移位，但FIFO的源码中的【按位与】是个例外
     */
    private static void cycleMovePosition(){
//        int[] array = {1, 2, 3, 4, 5, 6, 7};
        int[] array = new int[]{1, 2, 3, 4, 5, 6, 7,8,9,10,11,12,13,14,15,16};
        int arrayLength = array.length;
        int shiftAmount = 3;

        // 遍历数组，计算每个元素向右循环移位后的索引
        for (int i = 0; i < arrayLength; i++) {
            int originalIndex = i;
            int shiftedIndex = (originalIndex + shiftAmount) % arrayLength; // 使用模运算新索引
            System.out.println("Original index: " + originalIndex + ", Shifted index: " + shiftedIndex);
        }
        /**
         * 打印结果为：
         * Original index: 0, Shifted index: 3
         * Original index: 1, Shifted index: 4
         * Original index: 2, Shifted index: 5
         * Original index: 3, Shifted index: 6
         * Original index: 4, Shifted index: 0
         * Original index: 5, Shifted index: 1
         * Original index: 6, Shifted index: 2
         */
    }

    /**
     * 按位与：实现循环移位
     *
     * 注：这个使用方法比较特殊，仅在 DiyQueueFIFOByArray.java 的源码中可以使用，比如 分母为 15
     */
    private static void cycleMovePositionByFIFO(){
        int[] array = new int[]{1, 2, 3, 4, 5, 6, 7,8,9,10,11,12,13,14,15,16};

        int mask = array.length - 1;
        int shiftAmount = 3;

        // 遍历数组，计算每个元素向右循环移位后的索引
        for (int i = 0; i < array.length; i++) {
            int originalIndex = i;
            int shiftedIndex = (originalIndex + shiftAmount) & mask; // 使用 按位与 计算新索引
            System.out.println("Original index: " + originalIndex + ", Shifted index: " + shiftedIndex);
        }
    }

    /**
     * 轮转数组
     * nums = {1, 2, 3, 4, 5, 6, 7}   k = 3
     *
     * @return
     */
    private static int[] rotateArray(int[] nums, int k){
        Map<Integer,Integer> cycleMap = new HashMap<>();

        for(int i = 0; i < nums.length; i++){
            int shiftedIndex = (i + k) % nums.length;
            // 由于索引是唯一的，所以将索引作为 key
            cycleMap.put(shiftedIndex, nums[i]);
        }

        Iterator iterator = cycleMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry)iterator.next();
            nums[(Integer) entry.getKey()] = (Integer) entry.getValue();
        }
        return nums;
    }
}
