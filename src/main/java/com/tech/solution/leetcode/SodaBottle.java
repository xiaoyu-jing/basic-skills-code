package com.tech.solution.leetcode;

/**
 * @author jing1560
 * @data 2024/3/23
 *
 * 华为机试 - 【汽水瓶】 2023真题
 *
 * 题目：
 * 某商店规定：三个空汽水瓶可以换一瓶汽水，允许向老板借空汽水瓶（但是必须要归还）。
 * 小张手上有n个空汽水瓶，他想知道自己最多可以喝到多少瓶汽水。
 * 数据范围：输入的正整数满足 1 ≤ n ≤ 100
 *
 * 注意：本题存在多组输入。输入的 0 表示输入结束，并不用输出结果。
 *
 * 输入描述：
 * 输入文件最多包含 10 组测试数据，每个数据占一行，仅包含一个正整数 n（ 1<=n<=100 ），表示小张手上的空汽水瓶数。n=0 表示输入结束，你的程序不应当处理这一行。
 *
 * 输出描述：
 * 对于每组测试数据，输出一行，表示最多可以喝的汽水瓶数。如果一瓶也喝不到，输出0。
 *
 * 示例1
 * 输入例子：
 * 3
 * 10
 * 81
 * 0
 * 输出例子：
 * 1
 * 5
 * 40
 * 例子说明：
 * 样例 1 （3个空瓶 最多可喝到1瓶汽水）解释：用三个空瓶换一瓶汽水，剩一个空瓶无法继续交换
 * 样例 2 （10个空瓶 最多可喝到5瓶汽水）解释：用九个空瓶换三瓶汽水，剩四个空瓶再用三个空瓶换一瓶汽水，剩两个空瓶，向老板借一个空瓶再用三个空瓶换一瓶汽水喝完得一个空瓶还给老板
 */
public class SodaBottle {

    public static void main(String[] args){
        maxCountSolution2(10);
        maxCountSolution2(3);
        maxCountSolution2(81);
        maxCountSolution2(0);
    }

    /**
     * 能喝到最多汽水的瓶数
     * @param emptySodaBottle   空汽水瓶数量
     * @return
     */
    private static int maxCountSolution1(int emptySodaBottle){
        if(emptySodaBottle == 0){
            return 0;
        }
        int maxCount = 0;
        int tempEmptyBottle = emptySodaBottle;
        while (true){
            // 取商
            int shang = tempEmptyBottle / 3;
            // 取余
            int yushu = tempEmptyBottle % 3;
            maxCount += shang;
            // 商 + 余  表示向上取整
            if((shang + yushu) == 1){
                // 剩1个空瓶的时候，无法再兑换了，直接退出
                break;
            } else if((shang + yushu) < 3){
                // 剩2个空瓶的时候，可以和老板借一个空瓶，然后再兑换一瓶 汽水。所以直接 加一即可，然后结束
                maxCount++;
                break;
            }
            tempEmptyBottle = (shang + yushu);
        }
        System.out.println(emptySodaBottle + " 个空瓶，最多可喝到 " + maxCount + " 瓶汽水");
        return maxCount;
    }


    /**
     * 能喝到最多汽水的瓶数
     * @param emptySodaBottle   空汽水瓶数量
     * @return
     */
    private static int maxCountSolution2(int emptySodaBottle){
        if(emptySodaBottle == 0){
            return 0;
        }
        int maxCount = 0;
        int tempEmptyBottle = emptySodaBottle;
        while (true){
            //向上取整的一种数学写法： (a + b - 1) / b
            int intShang = (tempEmptyBottle + 3 - 1) / 3;
            maxCount += intShang;
            if(intShang == 1){
                // 剩1个空瓶的时候，无法再兑换了，直接退出
                break;
            } else if(intShang < 3){
                //小于3的时候，将 向上取整 后 多加的一再减回去
                maxCount--;
                break;
            }
            tempEmptyBottle = intShang;
        }
        System.out.println(emptySodaBottle + " 个空瓶，最多可喝到 " + maxCount + " 瓶汽水");
        return maxCount;
    }

}
