package com.tech.solution.encoder;

import java.util.*;

/**
 * @author jing1560
 * @data 2024/1/11
 * @description 支持浮点数的全组合计算，得出最优解
 */
public class FloatAssembleTest {

    public static void main(String[] args) {
        //float[] nums = {1.1f, 2.44f*8, 3.8f*33, 4.0f, 5.66f}; // 示例输入
        //float[] nums = {238620f,446200f,1730790.4f,676090f,8259550f,2310637f,6456843.8f,5658475.6f,1622616f,67609f,51701f,18294.2f,755630f,801763.2f,2580198.06f,176101.56f,936981.2f,986296f,295888.8f,14794.44f,197259.2f,369861f,108492.56f,39451.84f,198850f,198850f,165443.2f,222712f,28634.4f,1558984f,38179.2f}; // 示例输入


//        float[] nums = {238620f,446200f,1730790.4f,676090f,8259550f,2310637f,6456843.8f,5658475.6f,1622616f,67609f,51701f,18294.2f,755630f,801763.2f,2580198.06f,176101.56f,936981.2f,986296f,295888.8f}; // 示例输入
        float[] nums = {14794.44f,197259.2f,369861f,108492.56f,39451.84f,198850f,198850f,165443.2f,222712f,28634.4f,1558984f,38179.2f}; // 示例输入
        Float targetData = 11163539.3f;

        System.out.println("555");
        try {
            List<Float> resultList = combineAndSum(nums,targetData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println("所有可能的和为：" + resultList);
    }

    private static List<Float> combineAndSum(float[] nums,Float targetData) {
        List<Float> resultList = new ArrayList<>();
        List<List<Float>> combinations = generateCombinations(nums);
        combinations.remove(0);
        List<String> strList = new ArrayList<>();
        System.out.println("123");
        for (List<Float> combination : combinations) {
            // 求和
            Float sumData = sum(combination);
            // 和指定的数字相比， 求差值，再取绝对值
            Float absData = Math.abs(sumData - targetData);
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

    private static List<List<Float>> generateCombinations(float[] nums) {
        List<List<Float>> combinations = new ArrayList<>();
        backtrack(combinations, new ArrayList<>(), nums,  0);
        return combinations;
    }

    private static void backtrack(List<List<Float>> combinations, List<Float> currentCombination, float[] nums, int start) {
        if (start == nums.length) {
            combinations.add(new ArrayList<>(currentCombination));
            return;
        }

        backtrack(combinations, currentCombination, nums, start + 1); // 不选取当前位置的数字
        currentCombination.add(nums[start]); // 选取当前位置的数字
        backtrack(combinations, currentCombination, nums, start + 1); // 继续下一位置的递归调用
        currentCombination.remove(currentCombination.size() - 1); // backtrack，回溯，移除最后一个添加的元素
    }

    private static float sum(List<Float> combination) {
        float sum = 0f;
        for (float num : combination) {
            sum += num;
        }
        return sum;
    }

}
