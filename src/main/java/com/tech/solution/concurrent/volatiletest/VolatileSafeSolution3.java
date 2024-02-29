package com.tech.solution.concurrent.volatiletest;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author jing1560
 * @data 2024/2/28
 * 针对 Volatile 多线程场景下不安全的情况的解决方法1 -- 使用 AtomicInteger
 */
public class VolatileSafeSolution3 {

    public AtomicInteger inc = new AtomicInteger(0);

    public void increase(){
        inc.getAndIncrement();
    }

    public static void main(String[] args){
        final VolatileSafeSolution3 volatileSafeSolution3 = new VolatileSafeSolution3();
        for(int i=0; i<10; i++){
            new Thread(){
                public void run(){
                    for(int j=0; j<1000; j++){
                        volatileSafeSolution3.increase();
                    }
                }
            }.start();
//            System.out.println(Thread.activeCount());
        }

        System.out.println("主线程挂起");
        //activeCount 返回的预估线程组，此处必须为2， 子线程组+父线程
        while (Thread.activeCount() > 2) { //保证前面的线程都执行完
            //主线程让出CPU占有权
            Thread.yield();
        }
        /*try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

//        System.out.println(Thread.activeCount());
        System.out.println(volatileSafeSolution3.inc);

    }

}
