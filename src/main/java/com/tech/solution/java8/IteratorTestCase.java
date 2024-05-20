package com.tech.solution.java8;

import java.util.*;

/**
 * @author jing1560
 * @date 2024/3/4
 * @discription  迭代器测试
 */
public class IteratorTestCase {

    public static void main(String[] args){

//        testIterator();

//        testMapIterator();

        testListIterator();

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

    /**
     * 测试Map迭代器
     */
    private static void testMapIterator(){
        Map<String,String> map = new HashMap<>();
        map.put("1","Java");
        map.put("2","Python");
        map.put("3","Go");
        map.put("4","C++");
        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println(key + ":" + value);
        }
    }

    /**
     * 测试list迭代器
     */
    private static void testListIterator(){
        List<String> list = Arrays.asList("Java","Python","Rube","Go");
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String item = iterator.next();
            if("Python".equals(item)){
                iterator.remove();
            }
        }
        System.out.println(list);
    }

}
