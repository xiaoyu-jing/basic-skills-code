package com.tech.solution.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author jing1560
 * @data 2024/4/11
 *
 * 示例 1 ：
 *
 * 输入：num = "1432219", k = 3
 * 输出："1219"
 * 解释：移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219 。
 *
 * 示例 2 ：
 *
 * 输入：num = "10200", k = 1
 * 输出："200"
 * 解释：移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。
 * 示例 3 ：
 *
 * 输入：num = "10", k = 2
 * 输出："0"
 * 解释：从原数字移除所有的数字，剩余为空就是 0 。
 */
public class RemoveKdigits2 {

    public static void main(String[] args){
        System.out.println(removeKdigits("1432219", 3));
        System.out.println(removeKdigits("10200", 1));
        System.out.println(removeKdigits("10", 2));
    }

    private static String removeKdigits(String num, int k){
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i <= num.length() - k; i++){
            String subString = num.substring(i, i + k);
            String tempStr = "";
            if(k <= 1){
                tempStr = num.replaceFirst(subString,"");
            } else {
                tempStr = num.replaceAll(subString,"");
            }
            if("".equals(tempStr)){
                tempStr = "0";
            }
            list.add(Integer.valueOf(tempStr));
        }
        if(!list.isEmpty()){
            Collections.sort(list);
            return String.valueOf(list.get(0));
        }
        return "";
    }
}
