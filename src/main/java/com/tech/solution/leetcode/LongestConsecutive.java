package com.tech.solution.leetcode;

import java.util.*;

/**
 * @author jing1560
 * @data 2024/3/21
 *
 * 算法：最长连续序列
 *
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
 *
 * 示例 1：
 * 输入：nums = [100,4,200,1,3,2]
 * 输出：4
 * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
 *
 * 示例 2：
 * 输入：nums = [0,3,7,2,5,8,4,6,0,1]
 * 输出：9
 */
public class LongestConsecutive {

    public static void main(String[] args){
        System.out.println("[解法一]：" + longestConsecutive1(new int[]{100,4,200,1,3,2}));
        System.out.println("[解法一]：" + longestConsecutive1(new int[]{0,3,7,2,5,8,4,6,0,1}));
        System.out.println("[解法一]：" + longestConsecutive1(new int[]{0,3,7,2,5,8,4,6,4,0,1}));

        System.out.println("[解法二]：" + longestConsecutive2(new int[]{100,4,200,1,3,2}));
        System.out.println("[解法二]：" + longestConsecutive2(new int[]{0,3,7,2,5,8,4,6,0,1}));
        System.out.println("[解法二]：" + longestConsecutive2(new int[]{0,3,7,2,5,8,4,6,4,0,1}));

        System.out.println("[解法三]：" + longestConsecutive3(new int[]{100,4,200,1,3,2}));
        System.out.println("[解法三]：" + longestConsecutive3(new int[]{0,3,7,2,5,8,4,6,0,1}));
        System.out.println("[解法三]：" + longestConsecutive3(new int[]{0,3,7,2,5,8,4,6,4,0,1}));
    }

    /**
     * 解法一：排序 + List
     * @param nums
     * @return
     */
    private static int longestConsecutive1(int[] nums) {
        Arrays.sort(nums);   // 时间复杂度为 O(n log(n))
        List<Integer> countList = new ArrayList<>();
        // 最大长度
        int maxLength = 0;
        // 循环计数值
        int countIndex = 0;
        while (countIndex != nums.length){  // 时间复杂度为 O(n)
            if(!countList.isEmpty() && countList.get(countList.size() - 1) + 1 != nums[countIndex]){
                maxLength = Math.max(maxLength, countList.size());
                countList.clear();
                continue;
            }
            countList.add(nums[countIndex]);
            maxLength = Math.max(maxLength, countList.size());
            countIndex++;
        }
        return maxLength;
    }

    /**
     * 解法二：排序 + 栈
     * @param nums
     * @return
     */
    private static int longestConsecutive2(int[] nums) {
        Arrays.sort(nums);   // 时间复杂度为 O(n log(n))
        Deque<Integer> deque = new ArrayDeque<>();
        // 最大长度
        int maxLangth = 0;
        // 循环计数值
        int countIndex = 0;
        while (countIndex != nums.length) {  // 时间复杂度为 O(n)
            if(!deque.isEmpty() && deque.peekLast() + 1 != nums[countIndex]){
                maxLangth = Math.max(maxLangth, deque.size());
                deque.clear();
                continue;
            }
            deque.offerLast(nums[countIndex]);
            maxLangth = Math.max(maxLangth, deque.size());
            countIndex++;
        }
        return maxLangth;
    }

    /**
     * 解法三：Map计数
     * @param nums
     * @return
     */
    private static int longestConsecutive3(int[] nums) {
        if(nums.length == 0){
            return 0;
        }
        // map存放 <值，值的计数>
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            if(map.get(nums[i]) == null){
                map.put(nums[i], 1);
            } else {
                map.put(nums[i], map.get(nums[i]) + 1);
            }
        }
        int maxLangth = 0;
        int count = 1;
        for(int i = 0; i < nums.length; i++){    // 时间复杂度为 O(n)
            while (true){     // 这个while循环的时间复杂度为 O(1)
                // map中不存在 预期值 或 存在的预期值个数大于 1 时，表示序列不再 递增 且 连续，需要中断
                if(map.get(nums[i] + count) == null || map.get(nums[i] + count) > 1){
                    count = 1;
                    break;
                }
                // 此处需要先 +1，再求最大值， 表示先将 第一位数 计算在内
                count++;
                maxLangth = Math.max(maxLangth, count);
            }
        }
        return maxLangth;
    }

}
