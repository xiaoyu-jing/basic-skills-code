package com.tech.solution.common.designpattern.singletonpattern;

/**
 * @author jing1560
 * @data 2024/3/22
 *
 * 使用枚举（Enum）实现单例是最简单的方式，也能防止反射攻击和序列化问题。
 * 枚举的原理：https://www.jianshu.com/p/195a9265f5e1
 */
public enum SingletonEnum {

    INSTANCE;

    // 可以添加实例方法
}
