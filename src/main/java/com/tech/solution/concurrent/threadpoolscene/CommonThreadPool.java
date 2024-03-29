package com.tech.solution.concurrent.threadpoolscene;

/**
 * @author jing1560
 * @data 2024/3/2
 * 线程池使用方法汇总
 */
public class CommonThreadPool {

    public static void main(String[] args){
        threadTest();

//        runnableTest();
    }

    //Todo 线程的3种实现方式（Thread,Runnable,Callable）
    private static void threadTest(){
        for (int i = 0; i < 9; i++) {
            int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "_" + finalI);
                }
            }).start();
        }
    }

    private static void runnableTest(){

    }


    //Todo JDK的4种默认线程池


    //TODO 线程池写法1


    //TODO 线程池写法2
}
