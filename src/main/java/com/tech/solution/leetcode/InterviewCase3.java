package com.tech.solution.leetcode;

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
        System.out.println("方法1：最大利润为：" + maxProfit(new int[]{3,6,5,9,7}));

        System.out.println("方法2【优化算法】：最大利润为：" + maxProfit2(new int[]{3,6,5,9,7}));

    }

    /**
     * 方法1：时间复杂度为 O(n^2)
     * @param prices
     * @return
     */
    private static int maxProfit(int[] prices){
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

    /**
     * 方法2 - 优化算法：
     *       时间复杂度为 O(n)：其中 n 是数组 prices 的长度，因为只需要遍历一次数组
     *       空间复杂度为 O(1)：因为只使用了常量级别的额外空间
     * @param prices
     * @return
     */
    private static int maxProfit2(int[] prices){
        if(prices == null || prices.length == 0){
            return 0;
        }
        // 初始化最低价格为第一天的价格
        int minPrice = prices[0];
        // 初始化最大利润为0
        int maxProfit = 0;
        for(int i = 1; i < prices.length; i++){
            // 更新最低价格
            minPrice = Math.min(minPrice,prices[i]);

            // 计算并更新最大利润
            maxProfit = Math.max(maxProfit, prices[i] - minPrice);
        }
        return maxProfit;
    }

}
