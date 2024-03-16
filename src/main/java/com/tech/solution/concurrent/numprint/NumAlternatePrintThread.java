package com.tech.solution.concurrent.numprint;

/**
 * @author jing1560
 * @data 2024/2/29
 * 多线程 交替 且 顺序打印 1 ~ 100
 */
public class NumAlternatePrintThread extends Thread{
    // 线程数量
    private static final int THREAD_NUM = 6;
    // 计数，到100结束
    private static volatile int cnt = 0;
    // 线程编号
    private int id;

    public NumAlternatePrintThread(int id){
        this.id = id;
    }

    @Override
    public void run(){
        while (cnt < 100) {
            // cnt % THREAD_NUM 取余
            if(cnt % THREAD_NUM == id){
                //synchronized 保证原子性，volatile保证可见性，最终都是为了保证 cnt++ 操作的准确性
                synchronized (NumAlternatePrintThread.class) {
                    cnt++;
                    System.out.println("thread_" + id + " cnt:" + cnt);
                }
            }
        }
    }

    public static void main(String[] args){
        for(int i = 0; i < THREAD_NUM; i++){
            new NumAlternatePrintThread(i).start();
        }
    }

}
