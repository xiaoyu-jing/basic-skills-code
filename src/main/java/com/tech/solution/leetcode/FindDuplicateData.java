package com.tech.solution.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author jing1560
 * @data 2024/4/7
 *
 * 算法：寻找重复数
 *
 * 示例 1：
 * 输入：nums = [1,3,4,2,2]
 * 输出：2
 *
 * 示例 2：
 * 输入：nums = [3,1,3,4,2]
 * 输出：3
 *
 * 示例 3 :
 * 输入：nums = [3,3,3,3,3]
 * 输出：3
 */
public class FindDuplicateData {

    public static void main(String[] args){
        System.out.println("【暴力解法】[1,3,4,2,2] 存在的重复数为：" + findDuplicateSolution1(new int[]{1,3,4,2,2}));


        System.out.println("【快慢指针】[1,3,4,2,2] 存在的重复数为：" + findDuplicateSolution2(new int[]{1,3,4,2,2})
                + ", 环的长度为：" + calculateCycleLength(new int[]{1,3,4,2,2}));

        System.out.println("【快慢指针】[3,3,3,3,3] 存在的重复数为：" + findDuplicateSolution2(new int[]{3,3,3,3,3})
                + ", 环的长度为：" + calculateCycleLength(new int[]{3,3,3,3,3}));

        System.out.println("【快慢指针】[3,1,3,4,2] 存在的重复数为：" + findDuplicateSolution2(new int[]{3,1,3,4,2})
                + ", 环的长度为：" + calculateCycleLength(new int[]{3,1,3,4,2}));

        System.out.println("【快慢指针】[2,5,9,6,9,3,8,9,7,1] 存在的重复数为：" + findDuplicateSolution2(new int[]{2,5,9,6,9,3,8,9,7,1})
                + ", 环的长度为：" + calculateCycleLength(new int[]{2,5,9,6,9,3,8,9,7,1}));
    }

    /**
     * 方法一：暴力解法 - 哈希法
     * @param nums
     * @return
     */
    private static int findDuplicateSolution1(int[] nums){
        Set<Integer> visited = new HashSet<>();
        for(int i : nums){
            if(visited.contains(i)){
                return i;
            }
            visited.add(i);
        }
        return -1;
    }

    /**
     * 方法二：快慢指针   属于 「Floyd 判圈算法」（又称龟兔赛跑算法）
     *
     * 图文解释：https://blog.csdn.net/plokmju88/article/details/103675872
     * @param nums
     * @return
     */
    private static int findDuplicateSolution2(int[] nums){
        int slow = 0;
        int fast = 0;
        // 当 slow == fast 时，表示 两个指针的相遇点，但不一定是 入环点 （也就是此时不一定是 重复数字）
        do {
            // 使用 do - while 循环 先给 slow 和 fast 赋初始值
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        // 寻找入环点
        fast = slow;  // fast 指针指向当前 相遇点
        slow = 0;  // slow 指针指向链表头节点（也就是 数组的第一个）
        while (slow != fast) {
            // slow 和 fast 每次向前走一步，重合点 就是 入环点
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }

    /**
     * 环的长度计算
     * @param nums
     * @return
     */
    private static int calculateCycleLength(int[] nums){
        // duplicateData 为 重复数字（也就是入环点）
        int duplicateData = findDuplicateSolution2(nums);
        // 入环点 也就是 慢指针 的出发点   （由于 慢指针 每次向前走一步，所以此处直接借助慢指针的概念）
        int slow = duplicateData;
        // 环的长度
        int cycleLength = 0;
        do {
            // 环的初始值从0开始，由于 do - while 循环多执行了一次，所以 当(slow != duplicateData)条件不满足时，刚好 cycleLength 就是环的长度
            cycleLength++;
            slow = nums[slow]; // 每次向前走一步
        } while (slow != duplicateData);

        return cycleLength;
    }
}