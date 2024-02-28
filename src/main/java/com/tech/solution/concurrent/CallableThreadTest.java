package com.tech.solution.concurrent;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author jing1560
 * @data 2024/2/26
 */
public class CallableThreadTest {

    public static void main(String[] args){
//        callalbeTest1();

//        callalbeTest2();

        callalbeTest3();
    }

    /**
     * 效果：A ~ P 会乱序输出；
     *      先执行invokeAll方法，然后才会执行 callers中的 Override 方法
     *
     */
    public static void callalbeTest1(){
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<String> defaultValueList = Lists.newArrayList("A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P");
        ArrayList<Callable<String>> callers = new ArrayList<>();
        List<String> resultList = new ArrayList<>();
        for(String str : defaultValueList){
            callers.add(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    StringBuilder builder = new StringBuilder();
                    builder.append(str);
                    builder.append("-2024.02.26");
                    resultList.add(builder.toString());
                    System.out.println(builder.toString());
                    return null;
                }
            });
        }
        try {
            executorService.invokeAll(callers);

            executorService.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * 效果：A ~ P 会【顺序】输出；
     *      先执行invokeAll方法，然后才会执行 callers中的 Override 方法
     *
     */
    public static void callalbeTest2(){
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<String> defaultValueList = Lists.newArrayList("A","Y","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P");
        ArrayList<Callable<String>> callers = new ArrayList<>();
        for(String str : defaultValueList){
            callers.add(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    StringBuilder builder = new StringBuilder();
                    builder.append(str);
                    builder.append("-2024.02.26");
                    // 此处 A ~ P 会【乱序】输出
                    System.out.println(builder.toString());
                    //此处直接返回
                    return builder.toString();
                }
            });
        }
        try {
            // invokeAll方法返回的结果顺序 与 给定的参数列表的顺序相同
            List<Future<String>> futuresList = executorService.invokeAll(callers);
            System.out.println(futuresList.size());
            futuresList.stream().forEach(value -> {
                try {
                    // 此处 A ~ P 会【顺序】输出
                    System.out.println(value.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            });

            executorService.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * invokeAny方法测试
     * 效果：返回第一执行成功的数据
     *
     */
    public static void callalbeTest3(){
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<String> defaultValueList = Lists.newArrayList("A","Y","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P");
        ArrayList<Callable<String>> callers = new ArrayList<>();
        for(String str : defaultValueList){
            callers.add(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    StringBuilder builder = new StringBuilder();
                    builder.append(str);
                    builder.append("-2024.02.26");
                    // 此处 A ~ P 只会输出一部分
                    System.out.println(builder.toString());
                    //此处直接返回
                    return builder.toString();
                }
            });
        }
        try {
            String futuresResult = executorService.invokeAny(callers);
            System.out.printf("futuresResult:%s", futuresResult);
            executorService.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

}
