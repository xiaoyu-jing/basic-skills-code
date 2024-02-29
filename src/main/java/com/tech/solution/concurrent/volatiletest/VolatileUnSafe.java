package com.tech.solution.concurrent.volatiletest;

/**
 * @author jing1560
 * @data 2024/2/28
 * Volatile 多线程不安全效果
 */
public class VolatileUnSafe {

    //inc变量有 volatile,可以保证线程间的可见性
    public volatile int inc = 0;

    public void increase(){
        // ++的自增操作不具有原子性
        inc++;
    }

    public static void main(String[] args){
        final VolatileUnSafe volatileUnSafe = new VolatileUnSafe();
        for(int i=0; i<10; i++){
            new Thread(){
                public void run(){
                    for(int j=0; j<1000; j++){
                        volatileUnSafe.increase();
                    }
                }
            }.start();
//            System.out.println(Thread.activeCount());
        }

        System.out.println("主线程挂起");
        /*while (Thread.activeCount() > 1) //保证前面的线程都执行完
            //主线程让出CPU占有权
            Thread.yield();*/
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        System.out.println(Thread.activeCount());
        System.out.println(volatileUnSafe.inc);

    }
}
