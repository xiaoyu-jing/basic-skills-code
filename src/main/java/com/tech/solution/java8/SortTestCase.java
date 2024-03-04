package com.tech.solution.java8;

import java.util.Arrays;
import java.util.List;

/**
 * @author jing1560
 * @data 2024/3/4
 * @discription  排序测试
 */
public class SortTestCase {

    public static void main(String[] args){
        testSort1();
    }

    private static void testSort1(){
        //JDK1.8
        List<String> list = Arrays.asList("a","t","c","k");
        list.sort((v1,v2) -> v1.compareTo(v2));
        //System.out.printf("升序：%s",list);
        System.out.println("升序：" + list);

        list.sort((v1,v2) -> v2.compareTo(v1));
        System.out.println("降序：" + list);

        list.sort((v1,v2) -> {
            int result = v1.compareTo(v2);
            return result;
        });
        System.out.println("升序：" + list);
    }

}
