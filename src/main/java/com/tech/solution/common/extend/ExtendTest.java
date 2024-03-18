package com.tech.solution.common.extend;

/**
 * @author jing1560
 * @data 2024/3/18
 * JAVA单继承多实现的原因：https://blog.51cto.com/u_16099281/9085900
 */
public class ExtendTest implements InterfaceExtend{

    /**
     * 如果"多实现"的接口中，都存在同名的 say() 方法，子类实现时，只能出现一次实现的 say() 方法
     */
    @Override
    public void say() {
        System.out.println("implements multi extends of interface");
    }

    public static void main(String[] args){
        ExtendTest test = new ExtendTest();
        test.say();

        System.out.println(test.diffProp1);
        System.out.println(test.diffProp2);

        //编译期会报错：对sameProp的引用不明确，Interface1 和 Interface2 中都包含静态成员变量 sameProp
        //System.out.println(test.sameProp);

    }
}
