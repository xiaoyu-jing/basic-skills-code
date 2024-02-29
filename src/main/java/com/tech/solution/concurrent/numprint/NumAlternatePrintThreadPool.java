package com.tech.solution.concurrent.numprint;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @author jing1560
 * @data 2024/2/29
 * 多线程 交替 且 顺序打印 1 ~ 100，使用线程池
 */
public class NumAlternatePrintThreadPool {

    private static final int CPU_SIZE = Runtime.getRuntime().availableProcessors();
    private static final int CORE_SIZE = CPU_SIZE + 1;
    private static final int MAX_POOL_SIZE = 2 * CPU_SIZE;

    // 线程数量
    private static final int THREAD_NUM = 6;
    // 计数，到100结束
    private static volatile int cnt = 0;
    // 线程编号
    private static int id;

    public NumAlternatePrintThreadPool(int id){
        this.id = id;
    }

    /**
     * 构建线程池
     * @param poolName
     * @return
     */
    public static ExecutorService buildThreadPool(String poolName){
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat(poolName + "-pool-%d").build();

        ExecutorService exec = new ThreadPoolExecutor(
                CORE_SIZE,
                MAX_POOL_SIZE,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1000),
                threadFactory,
                new ThreadPoolExecutor.AbortPolicy()
        );

        return exec;
    }

    public static void main(String[] args){
        Runnable task = new Runnable() {
            @Override
            public void run() {
                while (cnt < 100){
                    if(cnt % THREAD_NUM == id) {
                        synchronized (NumAlternatePrintThreadPool.class) {
                            cnt++;
                            System.out.println(Thread.currentThread().getName()+ " thread_" + id + " cnt:" + cnt);
                        }
                    }
                }
            }
        };

        for(int i = 0; i < THREAD_NUM; i++){
            new NumAlternatePrintThreadPool(i).buildThreadPool("NumPrint").execute(task);
        }

        /*ExecutorService executorService = buildThreadPool("NumPrint");
        for(int i = 1; i <= 100; i++){
            final int number = i;
            // 提交任务给线程池进行处理
            executorService.execute(() -> System.out.println(Thread.currentThread().getName()+ " cnt:" + number));
        }

        // 关闭线程池
        executorService.shutdown();

        // 等待所有任务完成
        while (!executorService.isTerminated()){}*/

    }

}
