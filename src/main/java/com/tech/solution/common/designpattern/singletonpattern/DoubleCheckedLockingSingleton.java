package com.tech.solution.common.designpattern.singletonpattern;

/**
 * @author jing1560
 * @data 2024/3/15
 * 双重检查锁定（Double-Checked Locking）结合了懒汉式的延迟加载和线程安全，同时又减少了同步的开销，主要是用 synchronized 同步代码块来替代同步方法。
 */
public class DoubleCheckedLockingSingleton {

    /**
     * 在 instance 前加上 volatile 关键字，可以防止指令重排，
     * 因为 instance = new Singleton() 并不是一个原子操作，可能会被重排序，导致其他线程获取到未初始化完成的实例。
     */
    private static volatile DoubleCheckedLockingSingleton instance;

    private DoubleCheckedLockingSingleton(){}

    public static DoubleCheckedLockingSingleton getInstance(){
        if(instance == null){ // 当 instance 创建后，再次调用 getInstance 方法时，不会进入同步代码块，从而提高了性能。
            synchronized (DoubleCheckedLockingSingleton.class) {
                if(instance == null){
                    instance = new DoubleCheckedLockingSingleton();
                }
            }
        }
        return instance;
    }
}
