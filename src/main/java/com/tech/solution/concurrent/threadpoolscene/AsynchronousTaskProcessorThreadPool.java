package com.tech.solution.concurrent.threadpoolscene;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author jing1560
 * @data 2024/2/29
 * 异步任务通常涉及到I/O操作，比如数据库查询或文件读写，因此它们被视为I/O密集型任务。因此，我们将线程数设置为2 * CPU核心数。
 */
public class AsynchronousTaskProcessorThreadPool {
    //Java 中获取CPU核心数 （并非电脑自身的核心数，当前获取的JVM的CPU核心数，电脑是4核，intel的超线程技术,用闲置的寄存器虚拟处理器核心,每个硬件核心虚拟一个软件核心,就是8核心）
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int CORE_POOL_SIZE = 2 * CPU_COUNT;
    private static final int MAX_POOL_SIZE = 2 * CPU_COUNT * 2;

    public static final ThreadPoolExecutor exec = new ThreadPoolExecutor(
            CORE_POOL_SIZE,
            MAX_POOL_SIZE,
            10L,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(1000)
    );

    public static void main(String[] args){
        exec.execute(() -> {
            System.out.println("Async task started");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Async task completed");
        });

        System.out.println("Main thread continues to execute other operations");
        exec.shutdown();
    }
}
