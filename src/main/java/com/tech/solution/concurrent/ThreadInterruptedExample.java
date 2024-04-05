package com.tech.solution.concurrent;

/**
 * @author jing1560
 * @data 2024/4/5
 *
 * 【线程中断测试】
 *
 * Java 中的线程中断是一种线程间的协作模式，通过设置线程的中断标志并不能直接终止该线程的执行。被中断的线程会根据中断状态自行处理。
 * 1）void interrupt()方法：中断线程，例如，当线程 A 运行时，线程 B 可以调用线程 interrupt() 方法来设置线程的中断标志为 true 并立即返回。设置标志仅仅是设置标志, 线程 B 实际并没有被中断，会继续往下执行。
 * 2）boolean isInterrupted()方法：检测当前线程是否被中断。
 * 3）boolean interrupted() 方法：检测当前线程是否被中断，与 isInterrupted 不同的是，该方法如果发现当前线程被中断，则会清除中断标志。
 *
 *
 * 在Java中，当一个线程的interrupt方法被调用时，线程的中断状态（interrupted status）会被设置为true。这并不会立即停止线程，
 * 而是为线程提供了一个可以自行检查并处理中断请求的机会。线程可以在适当的时候查询自己的中断状态，并处理中断，
 * 通过Thread.currentThread().isInterrupted()或者Thread.interrupted()方法来检查中断状态。
 *
 * 如果线程被 wait, join, sleep调用之类的方法阻塞，当其他线程调用interrupt时，它会立即抛出InterruptedException，并清除其中断状态。
 * 因此，在这些方法抛出异常后，线程会处理中断请求，并继续执行。
 */
public class ThreadInterruptedExample extends Thread{

    public void run(){
        try {
            while (!Thread.currentThread().isInterrupted()){
                //如果线程被 wait,join,sleep 调用之类的方法阻塞，当其他线程调用interrupt时，它会立即抛出InterruptedException,并清除其中断状态。
                // 因此，在这些方法抛出异常后，线程会处理中断请求，并继续执行。
                Thread.sleep(300);
                System.out.println("子线程执行中.....");
            }
        } catch (InterruptedException e) {
            System.out.println("抛出异常：" + e);
        } finally {
            System.out.println("子线程执行完毕，线程清理");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadInterruptedExample thread = new ThreadInterruptedExample();
        thread.start();
        System.out.println("主线程休眠");
        Thread.sleep(1000); // 主线程休眠1秒
        thread.interrupt(); // 中断子线程
        while (true) {
            if(thread.isInterrupted()){
                System.out.println("子线程已中断，主线程继续执行");
                break;
            } else {
                System.out.println(".....子线程还未中断.....");
            }
        }
        System.out.println("主线程执行完毕！");
    }
}
