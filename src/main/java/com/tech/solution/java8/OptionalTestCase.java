package com.tech.solution.java8;

import java.util.Optional;

/**
 * @author jing1560
 * @data 2024/3/4
 * @discription Optional用来解决空指针异常
 *
 * 1、isPresent() 为true,表示存在，不为空；  为false，表示不存在，为空
 * 2、orElseGet() 中传入的是默认值，如果对象为空，则返回默认值；如果不为空，则返回自身值
 * 3、map()函数对当前Optional的值进行转化
 * 4、orElse() 接受一个默认值，而不是一个函数
 */
public class OptionalTestCase {

    public static void main(String[] args){

        testOptional1();

//        testOptional2_Map();

    }

    /**
     * isPresent()为true,表示存在，不为空；  为false，表示不存在，为空
     * Present 含义为 在场、出席
     */
    private static void testOptional1(){
        Object obj = null;
        Optional<Object> opt1 = Optional.ofNullable(obj);
        System.out.println("opt1 是否存在：" + opt1.isPresent());
        System.out.println(opt1.toString());  //不会发生空指针异常
        //System.out.println(obj.toString());   //会发生空指针异常

        String str = "Hello world";
        Optional<String> opt2 = Optional.ofNullable(str);
        System.out.println("opt2 是否存在：" + opt2.isPresent());

        //orElseGet中传入的是默认值，如果对象为空，则返回默认值；如果不为空，则返回自身值
        System.out.println("自动使用默认值：" + opt1.orElseGet(() -> "is Null").toString());
        System.out.println("不使用默认值：" + opt2.orElseGet(() -> "is Null").toString());

        System.out.println("直接输出 opt1：" + opt1.orElseGet(() -> "is Null"));
        System.out.println("直接输出 opt2：" + opt2.orElseGet(() -> "is Null"));

        System.out.println("opt1.orElse() 接收一个默认值而不是一个函数：" + opt1.orElse("恐龙抗狼"));
        System.out.println("opt2.orElse() 接收一个默认值而不是一个函数：" + opt2.orElse("恐龙抗狼"));
    }

    /**
     * map()函数对当前Optional的值进行转化。
     */
    private static void testOptional2_Map(){
        Optional<String> optional = Optional.ofNullable("You");
        //optional.map(xxx)   返回 Optional[How Old Are You?]
        //optional.map(xxx).get()  返回 How Old Are You?
        System.out.println(optional.map(value -> "How Old Are " + value + "?").get());
    }

}
