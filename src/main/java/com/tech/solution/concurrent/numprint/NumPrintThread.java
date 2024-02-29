package com.tech.solution.concurrent.numprint;

/**
 * @author jing1560
 * @data 2024/2/29
 * 多线程顺序打印 1 ~ 100
 */
public class NumPrintThread implements Runnable{
    //起始位置
    private int start;
    //结束位置
    private int end;
    public NumPrintThread(int start,int end){
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        synchronized (NumPrintThread.class) {
            for(int i = start; i <= end; i++){
                System.out.println("线程" + Thread.currentThread().getName() + "_" + i);
            }
        }
    }

    public static void main(String[] args){
        // 创建两个线程对象
        NumPrintThread numPrintThread1 = new NumPrintThread(1,50);
        NumPrintThread numPrintThread2 = new NumPrintThread(51,100);

        // 分别将线程对象传递给不同的线程进行执行
        Thread thread1 = new Thread(numPrintThread1);
        Thread thread2 = new Thread(numPrintThread2);

        // 开始执行线程
        thread1.start();
        thread2.start();
    }
}
