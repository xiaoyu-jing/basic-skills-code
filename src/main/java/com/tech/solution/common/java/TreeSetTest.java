package com.tech.solution.common.java;

import java.util.NavigableSet;
import java.util.TreeSet;

/**
 * @author jing1560
 * @data 2024/3/24
 *
 * TreeSet 最大的特点就是一个有顺序的去重集合容器。
 * 集合中的元素不保证插入顺序，而是默认使用元素的自然排序，不过可以自定义排序器
 *
 * TreeSet实现了NavigableSet接口，意味着它支持一系列的导航方法。比如查找与指定目标最匹配项。
 */
public class TreeSetTest {

    public static void main(String[] args){
        verifyTreeSetSort();

        verifyTreeSetSort2();

        //自定义排序
        verifyCustomSort();
    }

    /**
     * 验证TreeSet的顺序
     */
    private static void verifyTreeSetSort(){
        NavigableSet<Integer> set = new TreeSet<>();
        set.add(5);
        set.add(4);
        set.add(5);
        set.add(3);
        set.add(1);
        set.add(9);
        //正序遍历
        System.out.print("正序遍历：");
        set.forEach(item -> {
            System.out.print(item + " ");
        });
        System.out.println();

        //逆序遍历
        System.out.print("逆序遍历：");
        set.descendingIterator().forEachRemaining(item -> {
            System.out.print(item + " ");
        });
        System.out.println();
    }

    /**
     * 验证TreeSet的顺序
     */
    private static void verifyTreeSetSort2(){
        NavigableSet<Integer> set = new TreeSet<>();
        set.add(5);
        set.add(4);
        set.add(5);
        set.add(3);
        set.add(1);
        set.add(9);
        //正序遍历
        System.out.print("[verifyTreeSetSort2]正序遍历：");
        for(Integer item : set){
            System.out.print(item + " ");
        }
        System.out.println();
    }

    /**
     * 验证TreeSet的自定义排序
     */
    private static void verifyCustomSort(){
        NavigableSet<Integer> set = new TreeSet<>(((o1, o2) -> o1 - o2));
        set.add(5);
        set.add(4);
        set.add(5);
        set.add(3);
        set.add(1);
        set.add(9);
        //正序遍历
        System.out.print("[verifyTreeSetSort2]正序遍历：");
        for(Integer item : set){
            System.out.print(item + " ");
        }
        System.out.println();

        NavigableSet<Integer> deSet = new TreeSet<>(((o1, o2) -> o2 - o1));
        deSet.add(66);
        deSet.add(8);
        deSet.add(3);
        deSet.add(2);
        deSet.add(55);
        deSet.add(8);
        //逆序遍历
        System.out.print("[verifyCustomSort]逆序遍历：");
        for(Integer item : deSet){
            System.out.print(item + " ");
        }

    }
}
