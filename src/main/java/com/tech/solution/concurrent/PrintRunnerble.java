package com.tech.solution.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * @author jing1560
 * @data 2024/2/23
 */
public class PrintRunnerble {

    public static void main(String[] args) throws InterruptedException {
        FooBar fooBar = new FooBar(2);

        Thread threadFoo = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.print("foo");
            }
        });
        Thread threadBar = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.print("bar");
            }
        });
        fooBar.foo(threadFoo);
        fooBar.bar(threadBar);
        threadFoo.start();
        threadBar.start();

    }

    public static class FooBar {
        private static int n;

        private static CountDownLatch fooDone;
        private static CountDownLatch barDone;

//        private static final CyclicBarrier barrier = new CyclicBarrier(n);

        public FooBar(int n){
            this.n = n;
        }

        public static void foo(Runnable printFoo) throws InterruptedException {
            for(int i = 0; i < n; i++){
                fooDone = new CountDownLatch(1);
                barDone = new CountDownLatch(1);
                printFoo.run();
                fooDone.countDown();
                //barDone.await();
            }
        }

        public static void bar(Runnable printBar) throws InterruptedException {
            for(int i = 0; i < n; i++){
                fooDone.await();
//                barDone = new CountDownLatch(1);
                printBar.run();
                barDone.countDown();
            }
        }

    }
}
