package com.tech.solution.common.classloader;

/**
 * @author jing1560
 * @data 2024/3/15
 *
 * 静态代码块和构造方法执行优先顺序
 */
public class ClassLoaderCall {

    public static class Parent {
        static {
            System.out.println("父类-静态代码块");
        }

        public Parent(){
            System.out.println("父类-构造器");
        }
    }

    public static class Child extends Parent{
        static {
            System.out.println("子类-静态代码块");
        }

        public Child(){
            System.out.println("子类-构造器");
        }
    }

    /**
     * 打印结果为：
     * 父类-静态代码块
     * 子类-静态代码块
     * 父类-构造器
     * 子类-构造器
     * 父类-构造器
     * 子类-构造器
     *
     *
     * 总结：静态优先执行，父类优先于子类执行
     *      静态代码块是在JVM加载类的时候执行的，而且静态代码块执行且仅执行一次
     */
    public static void main(String[] args){
        Parent parent = new Child(); //打印：父类-静态代码块，子类-静态代码块，父类-构造器，子类-构造器
        parent = new Child(); //打印：父类-构造器，子类-构造器
    }
}
