package com.tech.solution.common.java;

import java.util.Arrays;

/**
 * @author jing1560
 * @data 2024/3/21
 * 字符串拼接
 */
public class StringJoinTestCase {

    public static void main(String[] args){
        stringJoinTest();

        java8StrJoin();
    }

    private static void stringJoinTest(){
        String code = "tan";
        String[] codeArr = code.split("");
        Arrays.sort(codeArr);
        //普通拼接
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < codeArr.length; i++){
            builder.append(codeArr[i]);
        }
        System.out.println("普通拼接：" + builder.toString());

        //通过逗号拼接、分隔
        StringBuilder builder1 = new StringBuilder();
        for(int i = 0; i < codeArr.length; i++){
            // 添加分隔符，但不在第一个元素之后添加
            if(builder1.length() > 0){
                builder1.append(",");
            }
            builder1.append(codeArr[i]);
        }
        System.out.println("通过逗号拼接：" + builder1.toString());
    }

    private static void java8StrJoin(){
        String code = "tan";
        String[] codeArr = code.split("");
        Arrays.sort(codeArr);
        String joinStr = String.join("", codeArr);
        System.out.println("Java8 拼接：" + joinStr);
    }
}
