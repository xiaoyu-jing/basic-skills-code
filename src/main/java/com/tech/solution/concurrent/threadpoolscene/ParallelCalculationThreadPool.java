package com.tech.solution.concurrent.threadpoolscene;

import org.checkerframework.checker.units.qual.C;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author jing1560
 * @data 2024/2/29
 * 并行计算任务主要用于计算，没有I/O阻塞，所以它们是CPU密集型的。线程数设置为CPU核心数 + 1
 */
public class ParallelCalculationThreadPool {
    //Java 中获取CPU核心数 （并非电脑自身的核心数，当前获取的JVM的CPU核心数，电脑是4核，intel的超线程技术,用闲置的寄存器虚拟处理器核心,每个硬件核心虚拟一个软件核心,就是8核心）
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int CORE_POOL_SIZE = CPU_COUNT + 1;
    private static final int MAX_POOL_SIZE = CPU_COUNT * 2;

    private static final ThreadPoolExecutor exec = new ThreadPoolExecutor(
            CORE_POOL_SIZE,
            MAX_POOL_SIZE,
            10L,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(1000));

    public static void main(String[] args){
        // 写法1
        /*Callable<Double> task = new Callable<Double>() {
            @Override
            public Double call() throws Exception {
                return Math.random() * 100;
          }
        };*/

        // 写法2
        /*Callable<Double> task = () -> {
            return Math.random() * 100;
        };*/

        // 写法3
        Callable<Double> task = () -> Math.random() * 100;

        List<Future<Double>> results = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            results.add(exec.submit(task));
        }

        for(Future<Double> result : results){
            try {
                System.out.println(result.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        exec.shutdown();

    }

}
