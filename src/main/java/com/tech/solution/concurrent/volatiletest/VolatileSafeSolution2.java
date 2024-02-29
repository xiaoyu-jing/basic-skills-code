package com.tech.solution.concurrent.volatiletest;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author jing1560
 * @data 2024/2/28
 * 针对 Volatile 多线程场景下不安全的情况的解决方法1 -- 使用 Lock
 */
public class VolatileSafeSolution2 {

    public int inc = 0;

    Lock lock = new ReentrantLock();

    public void increase(){
        lock.lock();
        try {
            inc++;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args){
        final VolatileSafeSolution2 volatileSafeSolution2 = new VolatileSafeSolution2();
        for(int i=0; i<10; i++){
            new Thread(){
                public void run(){
                    for(int j=0; j<1000; j++){
                        volatileSafeSolution2.increase();
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
        System.out.println(volatileSafeSolution2.inc);

    }

}
