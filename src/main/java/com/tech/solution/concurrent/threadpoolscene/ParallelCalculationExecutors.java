package com.tech.solution.concurrent.threadpoolscene;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author jing1560
 * @data 2024/2/29
 * 使用线程池进行并行的数值计算
 */
public class ParallelCalculationExecutors {

    private static final int THREAD_SIZE = 4;
    private static final ExecutorService exec = Executors.newFixedThreadPool(THREAD_SIZE);

    public static void main(String[] args){
        Callable<Double> task = new Callable<Double>() {
            @Override
            public Double call() throws Exception {
                // 这里模拟一些数值计算
                return Math.random() * 100;
            }
        };

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
