package com.labi.thread.chapter1.interrupt;

/**
 * 根据中断标志判断线程是否终止
 * 如下例子中子线程 thread 通过检查当前线程中断标志来控制是否退出循环
 */
public class InterruptThread {

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {
            // 如采当前线程被中断则退出循环
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println(Thread.currentThread() + " hello");
            }
        });

        thread.start();
        // 主线程休眠，让子线程在中断前能够执行输出
        Thread.sleep(1000);

        // 中断子线程
        System.out.println("main thread interrupt thread");
        thread.interrupt();

        // 等待子线程执行完毕
        thread.join();

        System.out.println("main is over");
    }

}
