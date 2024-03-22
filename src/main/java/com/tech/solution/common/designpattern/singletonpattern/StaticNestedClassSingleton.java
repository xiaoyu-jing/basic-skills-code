package com.tech.solution.common.designpattern.singletonpattern;

/**
 * @author jing1560
 * @data 2024/3/15
 *
 * 【单例模式的高阶玩法】 --- 不使用 双重检查锁定（synchronized）如何实现线程安全的延迟初始化
 *     利用 Java 的静态内部类（Static Nested Class）和类加载机制来实现 线程安全的延迟初始化。
 *
 * 当第一次加载 StaticNestedClassSingleton 类时并不会初始化 StaticNestedClassSingletonHolder，
 * 只有在第一次调用 getInstance 方法时才会导致 StaticNestedClassSingletonHolder 被加载，从而实例化 instance。
 */
public class StaticNestedClassSingleton {
    private StaticNestedClassSingleton(){}

    //静态内部类解决了延迟初始化的问题
    private static class StaticNestedClassSingletonHolder{
        // static 和 final 修饰的变量，解决了 线程安全性，final 修饰的变量 只有在声明的时候可以变更值，防止其他线程修改；同时final 防止了指令重排序
        private static final StaticNestedClassSingleton INSTANCE = new StaticNestedClassSingleton();
    }

    public static StaticNestedClassSingleton getInstance(){
        return StaticNestedClassSingletonHolder.INSTANCE;
    }
}
