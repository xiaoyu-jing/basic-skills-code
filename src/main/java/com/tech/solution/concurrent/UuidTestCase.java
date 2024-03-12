package com.tech.solution.concurrent;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author jing1560
 * @data 2024/3/12
 */
public class UuidTestCase {

    // 并发用户数（同时并发的线程数）
    private static final int threadNum = 10;

    // 发令枪
    private static CountDownLatch cdl = new CountDownLatch(threadNum);

    private static AtomicLong sum = new AtomicLong();

    public static void main(String[] args){
        for(int i = 0; i < threadNum; i++){
            new Thread(new OrderTread()).start();
            cdl.countDown();
        }
        try {
            Thread.currentThread().sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("总共线程执行时间： " + sum.get());
    }

    static class OrderTread implements Runnable {
        @Override
        public void run() {
            Long start = System.currentTimeMillis();
            try {
                cdl.await();
                for(int i = 0; i < 10; i++){
                    System.out.println("create OrderId : " + UUID.randomUUID().toString());
                }
                Long end = System.currentTimeMillis();
                sum.addAndGet(end - start);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
