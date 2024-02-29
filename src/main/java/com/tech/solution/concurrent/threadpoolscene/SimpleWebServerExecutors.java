package com.tech.solution.concurrent.threadpoolscene;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author jing1560
 * @data 2024/2/28
 * 模拟一个简单的Web服务器，接受请求并使用线程池进行处理。
 */
public class SimpleWebServerExecutors {
    private static final int NTHREADS = 100;
    private static final ExecutorService exec = Executors.newFixedThreadPool(NTHREADS);

    public static void main(String[] args){
        while (true) {
            //接收请求
            Runnable request = new Runnable() {
                @Override
                public void run() {
                    //处理请求
                    System.out.println("Request handled by " + Thread.currentThread().getName());
                }
            };
            exec.execute(request);
        }
    }

}
