package com.tech.solution.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jing1560
 * @data 2024/4/14
 *
 * 算法：除自身以外数组的乘积
 *
 * 给你一个整数数组 nums，返回 数组 answer ，其中 answer[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积 。
 * 题目数据 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内。
 * 请 不要使用除法，且在 O(n) 时间复杂度内完成此题。
 *
 * 示例 1:
 * 输入: nums = [1,2,3,4]
 * 输出: [24,12,8,6]
 *
 * 示例 2:
 * 输入: nums = [-1,1,0,-3,3]
 * 输出: [0,0,9,0,0]
 */
public class ProductExceptSelf {

    public static void main(String[] args){
        System.out.println("【使用乘法】：" + Arrays.toString(productExceptSelfSolution1(new int[]{1,2,3,4})));
        System.out.println("【使用乘法】：" + Arrays.toString(productExceptSelfSolution1(new int[]{-1,1,0,-3,3})));
        System.out.println("【使用乘法】：" + Arrays.toString(productExceptSelfSolution1(new int[]{-8,1,0,-3,0})));

        System.out.println("【使用除法】：" + Arrays.toString(productExceptSelfSolution2(new int[]{1,2,3,4})));
        System.out.println("【使用除法】：" + Arrays.toString(productExceptSelfSolution2(new int[]{-1,1,0,-3,3})));
        System.out.println("【使用除法】：" + Arrays.toString(productExceptSelfSolution2(new int[]{-8,1,0,-3,0})));

        System.out.println("【使用除法-优化】：" + Arrays.toString(productExceptSelfSolution3(new int[]{1,2,3,4})));
        System.out.println("【使用除法-优化】：" + Arrays.toString(productExceptSelfSolution3(new int[]{-1,1,0,-3,3})));
        System.out.println("【使用除法-优化】：" + Arrays.toString(productExceptSelfSolution3(new int[]{-8,1,0,-3,0})));
    }

    /**
     * 解法一：使用乘法
     * @param nums
     * @return
     */
    private static int[] productExceptSelfSolution1(int[] nums){
        int[] newArr = new int[nums.length];
        System.arraycopy(nums, 0, newArr, 0, nums.length);
        int[] resultArr = new int[nums.length];
        for(int i = 0; i < nums.length; i++){
            // 将当前位置为 1
            newArr[i] = 1;
            // reduce()方法接受一个BinaryOperator，它会对流中的元素进行累积
            int product = Arrays.stream(newArr).reduce((a,b) -> a * b).orElse(1);
            resultArr[i] = product;
            // 还原当前位的值
            newArr[i] = nums[i];
        }
        return resultArr;
    }

    /**
     * 解法二：使用除法
     *
     * 1、给数组中的所有非0元素乘积 A
     * 2、不论当前位是否等于0，只要数组中存在多个0时，任意一位都为 0
     * 3、数组中只有一个0的情况时，当前位置为0，则排除当前位置后的乘积为 A
     * 4、数组中不存在0的情况时，排除当前位置后的乘积为 A / 当前位置的值
     * @param nums
     * @return
     */
    private static int[] productExceptSelfSolution2(int[] nums){
        // 给数组中的 0 进行计数
        Map<Integer,Integer> zeroMap = new HashMap<>();
        //int[] zeroCount = new int[1];
        // 所有元素的乘积 初始值
        int product = 1;
        for(int num : nums){
            if(num == 0){
                //zeroCount[0]++;
                if(zeroMap.get(0) == null){
                    // 不存在时 进行初始化
                    zeroMap.put(0,1);
                } else {
                    // 存在时，进行 +1
                    zeroMap.put(0, zeroMap.get(0) + 1);
                }
                continue;
            }
            product *= num;
        }
        int[] resultArr = new int[nums.length];
        for(int i = 0; i < nums.length; i++){
            if(!zeroMap.isEmpty() && zeroMap.get(0) > 1){
                // 不论当前位是否等于0，只要数组中存在多个0时，任意一位都为 0
                resultArr[i] = 0;
            } else if(nums[i] == 0 && zeroMap.get(0) == 1){
                // 数组中存在1个0时，排除当前位后的乘积为 最大值
                resultArr[i] = product;
            } else if(zeroMap.get(0) == null){
                // 数组中不存在0时，排除当前位置后的乘积为 A / 当前位置的值
                resultArr[i] = product / nums[i];
            }
        }

        return resultArr;
    }

    /**
     * 解法二：使用除法 【优化逻辑】
     *
     * 1、给数组中的所有非0元素乘积 A
     * 2、不论当前位是否等于0，只要数组中存在多个0时，任意一位都为 0
     * 3、数组中只有一个0的情况时，当前位置为0，则排除当前位置后的乘积为 A
     * 4、数组中不存在0的情况时，排除当前位置后的乘积为 A / 当前位置的值
     * @param nums
     * @return
     */
    private static int[] productExceptSelfSolution3(int[] nums){
        // 给数组中的 0 进行计数
        int[] zeroCount = new int[1];
        // 所有元素的乘积 初始值
        int product = 1;
        for(int num : nums){
            if(num == 0){
                zeroCount[0]++;
                continue;
            }
            product *= num;
        }
        int[] resultArr = new int[nums.length];
        for(int i = 0; i < nums.length; i++){
            if(zeroCount[0] > 1){
                // 不论当前位是否等于0，只要数组中存在多个0时，任意一位都为 0
                resultArr[i] = 0;
            } else if(nums[i] == 0 && zeroCount[0] == 1){
                // 数组中存在1个0时，排除当前位后的乘积为 最大值
                resultArr[i] = product;
            } else if(zeroCount[0] == 0){
                // 数组中不存在0时，排除当前位置后的乘积为 A / 当前位置的值
                resultArr[i] = product / nums[i];
            }
        }

        return resultArr;
    }
}
