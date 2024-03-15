package com.tech.solution.common.designpattern.singletonpattern;

/**
 * @author jing1560
 * @data 2024/3/15
 * 懒汉式单例（Lazy Initialization）在实际使用时才创建实例，“确实懒”（😂）。这种实现方式需要考虑线程安全问题，因此一般会带上 synchronized 关键字。
 */
public class LazySingleton {

    private static LazySingleton instance;

    private LazySingleton(){}

    public static synchronized LazySingleton getInstance(){
        if(instance == null){
            instance = new LazySingleton();
        }
        return instance;
    }

}
