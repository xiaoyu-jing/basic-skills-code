package com.tech.solution.java8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author jing1560
 * @data 2024/3/5
 * @discription Java8 链式编程
 */
public class StreamTestCase {

    public static void main(String[] args){
        testStream1();
    }

    public static class Stream {
        private enum Status {
            OPEN,CLOSED
        };

        /**
         * final类不能被继承，没有⼦类，final类中的⽅法默认是final的。但该 final类可以继承别的类
         * final⽅法不能被⼦类的⽅法覆盖，但可以被继承。  一个final类中的所有成员方法都默认为 final，相当于 单独给 每个方法 加final修饰符
         * final成员变量表⽰常量，只能被赋值⼀次，赋值后值不再改变。
         * final不能⽤于修饰构造⽅法。
         * 注意：⽗类的private成员⽅法是不能被⼦类⽅法覆盖的，因此private类型的⽅法默认是final类型的。
         *
         *
         * 1、final类
         *    final类不能被继承，因此final类的成员⽅法没有机会被覆盖，默认都是final的。
         *    在设计类时候，如果这个类不需要有⼦类，类的实现细节不允许改变，并且确信这个类不会载被扩展，那么就设计为final类。
         *    【特别注意】：final类中的 数据变量 不受 final类的影响，依旧可以修改值
         *
         * 2、final方法
         *    如果⼀个类不允许其⼦类覆盖某个⽅法，则可以把这个⽅法声明为final⽅法。使⽤final⽅法的原因有⼆：
         *    ① 把⽅法锁定，防⽌任何继承类修改它的意义和实现。
         *    ② ⾼效，编译器在遇到调⽤final⽅法时候会转⼊内嵌机制，⼤⼤提⾼执⾏效率。
         *
         * 3、final变量（常量）
         *    ⽤final修饰的成员变量表⽰常量，值⼀旦给定就⽆法改变；
         *    final修饰的变量有三种：静态变量、实例变量和局部变量，分别表⽰三种类型的常量。从下⾯的例⼦中可以看出，⼀旦给final变量初值后，值就不能再改变了。
         *
         * 4、final参数
         *    当函数参数为final类型时，你可以读取使⽤该参数，但是⽆法改变该参数的值。
         *    ps:
         *        public void f1(final int i){
         *            i++;     //报错，i是final类型，值不允许改变
         *            System.out.println(i);
         *        }
         *
         */
        private static final class Task {
            private Status status;
            private Integer points;

            Task(final Status status,final Integer points){
                this.status = status;
                this.points = points;
            }

            public Status getStatus() {
                return status;
            }

            public void setStatus(Status status) {
                this.status = status;
            }

            public Integer getPoints() {
                return points;
            }

            public void setPoints(Integer points) {
                this.points = points;
            }

            public String toString(){
                return String.format("[%s, %d]", status, points);
            }
        }

    }

    public static void testStream1(){
        final List<Stream.Task> tasks = Arrays.asList(
                new Stream.Task(Stream.Status.OPEN, 5),
                new Stream.Task(Stream.Status.OPEN, 15),
                new Stream.Task(Stream.Status.CLOSED, 8)
                );
        java.util.stream.Stream<Stream.Task> stream = tasks.stream();
        java.util.stream.Stream<Stream.Task> filter
                = stream.filter(task -> task.getStatus() == Stream.Status.OPEN);
        //mapToInt函数，表示取值
        IntStream ints = filter.mapToInt(Stream.Task::getPoints);

        int totalPoints = ints.sum();

        System.out.println("totalPoints:" + totalPoints);

        //写法2
        int sum = tasks.stream()
                .filter(task -> task.getStatus() == Stream.Status.OPEN)
                .mapToInt(Stream.Task::getPoints)
                .sum();
        System.out.println("sum:" + sum);

        /*//被final修饰的变量，只能赋值一次，第二次赋值就会报错，如下：
        tasks = Arrays.asList(
                new Stream.Task(Stream.Status.CLOSED,123)
        );*/

        // final类中的 数据变量 不受 final类的影响，依旧可以修改值
        Stream.Task taskTemp = new Stream.Task(Stream.Status.OPEN,99);
        System.out.println("修改前：" + taskTemp.toString());
        taskTemp.setPoints(100);
        System.out.println("修改后：" + taskTemp.toString());

    }

}
