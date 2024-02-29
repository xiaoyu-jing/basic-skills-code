package com.tech.solution.concurrent.volatiletest;

/**
 * @author jing1560
 * @data 2024/2/28
 * 针对 Volatile 多线程场景下不安全的情况的解决方法1 -- 使用 Synchronized
 */
public class VolatileSafeSolution1 {

    public int inc = 0;

    public synchronized void increase(){
        inc++;
    }

    public static void main(String[] args){
        final VolatileSafeSolution1 volatileSafeSolution1 = new VolatileSafeSolution1();
        for(int i=0; i<10; i++){
            new Thread(){
                public void run(){
                    for(int j=0; j<1000; j++){
                        volatileSafeSolution1.increase();
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
        System.out.println(volatileSafeSolution1.inc);

    }

}
