package com.tech.solution.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author jing1560
 * @data 2024/3/21
 * 【最长连续序列】
 *
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 *
 * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [100,4,200,1,3,2]
 * 输出：4
 * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
 * 示例 2：
 *
 * 输入：nums = [0,3,7,2,5,8,4,6,0,1]
 * 输出：9
 */
public class LongestConsecutive {

    public static void main(String[] args){
        int longestNum1 = solution1(new int[]{100,4,200,1,3,2});
        System.out.println("方法1：连续序列最大长度为" + longestNum1);

        int longestNum2 = solution2(new int[]{0,3,7,2,5,8,4,6,0,1});
        System.out.println("方法2：连续序列最大长度为" + longestNum2);
    }

    /**
     * 方法1：时间复杂度为 O(n^2)
     *       没进行排序，直接递增比较
     */
    public static int solution1(int[] nums){
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            map.put(nums[i],0);
        }
        //能获取到的最长序列长度
        int maxCount = 0;
        for(int j = 0; j < nums.length; j++){
            //单次循环中的长度累计值
            int count = 1;
            // k为计数器，最大值即为数组的长度
            int k = 1;
            while (k < nums.length){
                if(map.get(nums[j] + k) != null){
                    count += 1;
                    k++;
                }else {
                    break;
                }
            }
            maxCount = Math.max(count,maxCount);
        }
        return maxCount;
    }

    /**
     * 方法2：时间复杂度为 O(n log(n))
     *       通过压栈的方式实现
     */
    public static int solution2(int[] nums){
        if(nums == null || nums.length < 1){
            return 0;
        }
        //该排序的时间复杂度为 O(n log(n))
        Arrays.sort(nums);
        Stack stack = new Stack();
        //初始栈中有1个元素
        stack.push(1);
        int maxCount = 0;
        //当 nums=[0] 时，应该输出 1，而不是0
        maxCount = Math.max(maxCount, stack.size());
        for(int i = 0; i < nums.length - 1; i++){
            if(nums[i] == nums[i + 1]){
                //nums[i] == nums[i+1] 应对前后是相同数字的场景，比如 nums = [0,1,1,2],输出的应该是 3，而不是2
                continue;
            }
            if((nums[i] + 1) == nums[i+1]){
                //后者比前者大1，则进栈一个元素
                stack.push(1);
                maxCount = Math.max(maxCount, stack.size());
            }else {
                //只要有一个不满足条件，就清空 栈
                stack.clear();
                //清空栈之后，栈里还需要初始化一个 数据
                stack.push(1);
            }
        }
        return maxCount;
    }

}
