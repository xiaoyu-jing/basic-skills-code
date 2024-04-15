package com.tech.solution.leetcode;

import java.util.Arrays;

/**
 * @author jing1560
 * @data 2024/4/15
 *
 * 算法：最大数
 *
 * 给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。
 * 注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。
 *
 * 示例 1：
 * 输入：nums = [10,2]
 * 输出："210"
 *
 * 示例 2：
 * 输入：nums = [3,30,34,5,9]
 * 输出："9534330"
 */
public class LargestNumber {

    public static void main(String[] args){
        System.out.println("[10,2] 最大数为：" + largestNumber(new int[]{10,2}));
        System.out.println("[3,30,34,5,9] 最大数为：" + largestNumber(new int[]{3,30,34,5,9}));
        System.out.println("[1,5,30,3,2,9,7] 最大数为：" + largestNumber(new int[]{1,5,30,3,2,9,7}));
    }

    private static String largestNumber(int[] nums) {
        // Arrays.sort 排序中需要接收 包装类，不能直接接收 int ，所以需要做一道转换
        Integer[] intNums = new Integer[nums.length];
        // 此处的数组拷贝，只能用 for 循环，不能用 System.arraycopy , 从 int 转为 Integer，在 System.arraycopy 中会报错
        for(int i = 0; i < nums.length; i++){
            intNums[i] = nums[i];
        }

        //自定义排序---将字符串自动排序，首位大的字符串排在第一位
        Arrays.sort(intNums, (a,b) -> {
            String str1 = String.valueOf(a);
            String str2 = String.valueOf(b);
            return (str2 + str1).compareTo(str1 + str2);  //排序为 倒序最大值
//            return (str1 + str2).compareTo(str2 + str1); //排序为 正序最小值
        });

        StringBuilder builder = new StringBuilder();
        Arrays.stream(intNums).forEach(num -> builder.append(num));
        return builder.toString();
    }
}
