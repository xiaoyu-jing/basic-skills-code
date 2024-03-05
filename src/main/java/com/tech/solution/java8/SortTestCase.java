package com.tech.solution.java8;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jing1560
 * @data 2024/3/4
 * @discription  排序测试
 */
public class SortTestCase {

    public static void main(String[] args){
//        testSort1();

        testSort2();
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

    private static void testSort2(){
        // -----------------JDK7 的排序----------------------
        //方法1
        List<Integer> list = Arrays.asList(8,3,11,5,23,7);
        System.out.println("原始 list：" + list);
        Collections.sort(list);  // 默认升序
        System.out.println("排序后的list:" + list);

        //方法2
        List<Integer> list1 = Arrays.asList(8,3,11,5,23,7);
        Collections.sort(list1, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1); //倒序排列
            }
        });
        System.out.println("排序后的list1: " + list1);

        //方法3
        List<Integer> list2 = Arrays.asList(8,3,11,5,23,7);
        Collections.sort(list2, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 == o2 ? 0 : (o1 > o2 ? 1 : -1); // o1 > o2 取 1，表示前者大于后者 则交换顺序，为升序
            }
        });
        System.out.println("排序后的list2: " + list2);

        // -----------------JDK8 的排序----------------------
        List<Integer> list3 = Arrays.asList(8,3,11,5,23,7);
        List<Integer> collect = list3.stream().sorted((o1, o2) -> o2.compareTo(o1)).collect(Collectors.toList());
        System.out.println("排序后的list3: " + collect);
    }

}
