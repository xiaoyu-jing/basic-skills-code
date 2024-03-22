package com.tech.solution.common.designpattern.singletonpattern;

/**
 * @author jing1560
 * @data 2024/3/15
 * 饿汉式单例（Eager Initialization）在类加载时就急切地创建实例，不管你后续用不用得到，这也是饿汉式的来源，简单但不支持延迟加载实例。
 */
public class EagerSingleton {

    private static final EagerSingleton instance = new EagerSingleton();

    //饿汉式 的构造器要设置为 private，防止外部对象 直接创建 EagerSingleton
    private EagerSingleton(){}

    public static  EagerSingleton getInstance(){
        return instance;
    }
}
