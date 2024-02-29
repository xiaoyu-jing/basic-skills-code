package com.tech.solution.concurrent.threadpoolscene;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author jing1560
 * @data 2024/2/29
 * 模拟处理异步任务
 *
 * 虽然 JDK 提供了快速创建线程池的方法，但其实不推荐使用 Executors 来创建线程池，
 * 因为从上面构造线程池的代码可以看出，newFixedThreadPool 线程池由于使用了 LinkedBlockingQueue，队列的容量默认无限大，实际使用中出现任务过多时会导致内存溢出；
 * newCachedThreadPool 线程池由于核心线程数无限大，当任务过多的时候会导致创建大量的线程，可能机器负载过高导致服务宕机。
 */
public class AsynchronousTaskProcessorExecutors {
    private static final ExecutorService exec = Executors.newCachedThreadPool();

    public static void main(String[] args){
        exec.execute(() -> {
            // 执行某些异步任务
            // 第二次打印 （原因：子线程的启动，要先从线程池中获取线程后 才能执行任务，速度要慢于 主线程，所以主线程先打印）
            System.out.println("Async task started");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 第三次打印
            System.out.println("Async task completed");
        });

        // 第一次打印
        System.out.println("Math thread continues to execute other operations.");
        exec.shutdown();
    }

}
