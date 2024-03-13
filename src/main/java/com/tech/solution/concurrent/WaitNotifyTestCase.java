package com.tech.solution.concurrent;

/**
 * @author jing1560
 * @data 2024/3/13
 */
public class WaitNotifyTestCase {

    /**
     * 打印结果为：线程1：我要等待
     *           线程2：我要唤醒
     *           线程2：我已经唤醒了
     *           线程1：我被唤醒了
     *
     * 分析：lock.wait(); 会将线程从 用户态 转为 内核态
     *      lock.notify();  会将线程从 内核态 转为 用户态，中间存在一定的转换时间，
     *                      所以 "线程2：我已经唤醒了" 先被打印出来；"线程1：我被唤醒了" 后被打印出来
     */
    public static void main(String[] args){
        Object lock = new Object();
        new Thread(()-> {
            synchronized (lock) {
                System.out.println("线程1：我要等待");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程1：我被唤醒了");
            }
        }).start();
        new Thread(() -> {
            synchronized (lock) {
                System.out.println("线程2：我要唤醒");
                lock.notify();
                System.out.println("线程2：我已经唤醒了");
            }
        }).start();
    }
}
