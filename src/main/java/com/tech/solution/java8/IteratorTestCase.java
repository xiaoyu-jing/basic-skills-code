package com.tech.solution.java8;

import java.util.Arrays;

/**
 * @author jing1560
 * @data 2024/3/4
 * @discription  迭代器测试
 */
public class IteratorTestCase {

    public static void main(String[] args){

        testIterator();

    }


    /**
     * 测试遍历
     */
    private static void testIterator(){
        Arrays.asList("a","b","c").forEach(e -> System.out.println(e));

        System.out.println("------------------");

        //Java8 的函数编程写法
        Arrays.asList("a","b","c").forEach(System.out::println);
    }
}
