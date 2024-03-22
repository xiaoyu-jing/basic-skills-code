package com.tech.solution.leetcode;

import java.util.Arrays;

/**
 * @author jing1560
 * @data 2024/3/21
 * 【移动零】
 *
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
 *
 * 示例 1:
 * 输入: nums = [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 *
 * 示例 2:
 * 输入: nums = [0]
 * 输出: [0]
 */
public class MoveZeroTestCase {

    public static void main(String[] args){
//        int[] result = moveZeroes(new int[]{1, 0, 0, 1});
//        int[] result = moveZeroes(new int[]{0, 0, 0, 1});
        int[] result = moveZeroes(new int[]{-58305,-22022,0,0,0,0,0,-76070,42820,48119,0,95708,-91393,60044,0,-34562,0,-88974});
        System.out.println(Arrays.toString(result));

//        int[] result2 = moveZeroesSolution2(new int[]{1, 0, 0, 1});
        int[] result2 = moveZeroesSolution2(new int[]{-58305,-22022,0,0,0,0,0,-76070,42820,48119,0,95708,-91393,60044,0,-34562,0,-88974});
        System.out.println(Arrays.toString(result2));
    }

    /**
     * 方法1：暴力循环
     * @param nums
     * @return
     */
    public static int[] moveZeroes(int[] nums) {
        //判断当前数组是否不全为0，sum大于0，表示不全为0
        int sum = Arrays.stream(nums).sum();
        //双重循环过滤第一遍
        extractedFilter(nums);
        //防止起始值为0，二次过滤
        while(sum >= 1 && nums[0] == 0){
            extractedFilter(nums);
        }
        // 最后兜底，三次过滤
        extractedFilter(nums);
        return nums;
    }

    private static void extractedFilter(int[] nums) {
        for(int i = 0; i < nums.length; i++){
            for(int j = i; j < nums.length - 1; j++){
                if(nums[j] == 0){
                    int temp = nums[j+1];
                    nums[j+1] = nums[j];
                    nums[j] = temp;
                } else {
                    continue;
                }
            }
        }
    }

    /**
     * 方法2：双指针
     * 【思路及解法】
     *
     * 使用双指针，左指针指向当前已经处理好的序列的尾部，右指针指向待处理序列的头部。
     * 右指针不断向右移动，每次右指针指向非零数，则将左右指针对应的数交换，同时左指针右移。
     * 注意到以下性质：
     *
     * 1）左指针左边均为非零数；
     * 2）右指针左边直到左指针处均为零。
     *
     * 因此每次交换，都是将左指针的零与右指针的非零数交换，且非零数的相对顺序并未改变。
     * @param nums
     */
    private static int[] moveZeroesSolution2(int[] nums){
        int numLength = nums.length;
        //双指针的起始都指向同一个位置，都指向0
        int left = 0;
        int right = 0;
        while (right < numLength) {
            //交换逻辑是：后者和前者交换
            //右指针要比左指针跑的快
            if(nums[right] != 0){
                swap(nums,left,right);
                //左指针要有条件往后移
                left++;
            }
            //右指针要无条件往后移
            right++;
        }
        return nums;
    }
    //左右交换
    private static void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }

}
