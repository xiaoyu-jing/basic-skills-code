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
    }

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
}
