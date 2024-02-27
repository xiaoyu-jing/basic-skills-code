package com.tech.solution.encoder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author jing1560
 * @data 2024/1/8
 * @description 支持整数的全组合计算，得出最优解
 */
public class IntAssebleTest {

    public static void main(String[] args) {
        int[] nums = {1, 2*8, 3*33, 4, 5}; // 示例输入
        Integer targetData = 55;
        
        List<Integer> resultList = combineAndSum(nums,targetData);
        //System.out.println("所有可能的和为：" + resultList);
    }

    private static List<Integer> combineAndSum(int[] nums,Integer targetData) {
        List<Integer> resultList = new ArrayList<>();
        List<List<Integer>> combinations = generateCombinations(nums);
        combinations.remove(0);
        List<String> strList = new ArrayList<>();
        for (List<Integer> combination : combinations) {
            // 求和
            Integer sumData = sum(combination);
            // 和指定的数字相比， 求差值，再取绝对值
            int absData = Math.abs(sumData - targetData);
            //System.out.println("差值：" + absData + ", 总额：" + sumData + ", 组合值为：" + combination);
            strList.add("差值：" + absData + ", 总额：" + sumData + ", 组合值为：" + combination);

            resultList.add(sumData);
        }
        //文本自然排序，打印
        Collections.sort(strList);
        strList.stream().forEach(str -> {
            System.out.println(str);
        });

        return resultList;
    }

    private static List<List<Integer>> generateCombinations(int[] nums) {
        List<List<Integer>> combinations = new ArrayList<>();
        backtrack(combinations, new ArrayList<>(), nums, 0);
        return combinations;
    }

    private static void backtrack(List<List<Integer>> combinations, List<Integer> currentCombination, int[] nums, int start) {
        if (start == nums.length) {
            combinations.add(new ArrayList<>(currentCombination));
            return;
        }

        backtrack(combinations, currentCombination, nums, start + 1); // 不选取当前位置的数字
        currentCombination.add(nums[start]); // 选取当前位置的数字
        backtrack(combinations, currentCombination, nums, start + 1); // 继续下一位置的递归调用
        currentCombination.remove(currentCombination.size() - 1); // backtrack，回溯，移除最后一个添加的元素
    }

    private static int sum(List<Integer> combination) {
        int sum = 0;
        for (int num : combination) {
            sum += num;
        }
        return sum;
    }
}
