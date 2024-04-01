package com.tech.solution.leetcode;

import java.util.Arrays;

/**
 * @author jing1560
 * @data 2024/4/1
 *
 * 算法：零钱兑换
 */
public class CoinChange {

    public static void main(String[] args){
        //System.out.println(coinChange(new int[]{1,2,5}, 11));
        System.out.println(coinChange(new int[]{186,419,83,408}, 6249));
    }

    public static int coinChange(int[] coins, int amount){
        if(amount == 0){
            return 0;
        }
        int coinCount = 0;
        Arrays.sort(coins);
        while(amount > 0){
            for(int j = coins.length - 1; j >= 0; j--){
                if(amount - coins[j] >= 0){
                    coinCount++;
                    amount = amount - coins[j];
                    break;
                }
            }
            // 防止死循环，当总金额小于数组中的最小值时，表示已经没有硬币可以匹配了
            if(amount < coins[0]){
                break;
            }
        }
        if(amount > 0){
            return -1;
        }
        return coinCount;
    }
}
