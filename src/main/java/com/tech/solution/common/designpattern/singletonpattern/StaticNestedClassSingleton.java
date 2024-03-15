package com.tech.solution.common.designpattern.singletonpattern;

/**
 * @author jing1560
 * @data 2024/3/15
 * 利用 Java 的静态内部类（Static Nested Class）和类加载机制来实现线程安全的延迟初始化。
 *
 * 当第一次加载 Singleton 类时并不会初始化 SingletonHolder，
 * 只有在第一次调用 getInstance 方法时才会导致 SingletonHolder 被加载，从而实例化 instance。
 */
public class StaticNestedClassSingleton {
    private StaticNestedClassSingleton(){}

    private static class StaticNestedClassSingletonHolder{
        private static final StaticNestedClassSingleton INSTANCE = new StaticNestedClassSingleton();
    }

    public static StaticNestedClassSingleton getInstance(){
        return StaticNestedClassSingletonHolder.INSTANCE;
    }
}
