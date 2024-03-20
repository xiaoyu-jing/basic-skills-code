package com.tech.solution.leetcode;

import java.util.Arrays;

/**
 * @author jing1560
 * @data 2024/3/20
 * 瓜子二手车 二面算法
 */
public class InterviewCase3 {

    /**
     * 题目：
     * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
     * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
     *
     * 示例：输入：[3,6,5,9,7]  输出：6
     * 假如在 第一天 - 3块钱 买入，在第四天 - 9块钱 卖出，计算出能获取的最大利润
     */

    public static void main(String[] args){
        System.out.println("最大利润为：" + maxMoney(new int[]{3,6,5,9,7}));
    }

    private static int maxMoney(int[] prices){
        int maxMoney = 0;
        for(int i = 0; i < prices.length; i++){
            for(int j = i+1; j < prices.length; j++){
                //获得最大利润的场景下，一定是后面的数大于前面的数，否则就是亏钱了
                if(prices[j] > prices[i]){
//                    int absValue =  Math.abs(prices[i] - prices[j]);
                    int absValue =  prices[j] - prices[i];
                    maxMoney = Math.max(absValue,maxMoney);
                }
            }
        }
        return maxMoney;
    }
    
    private static int maxMoney2(int[] prices){

        Arrays.stream(prices).max().getAsInt();

        return 0;
    }

}
