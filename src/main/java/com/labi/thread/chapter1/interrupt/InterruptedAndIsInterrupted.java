package com.labi.thread.chapter1.interrupt;

/**
 * 在interrupted()方法内部是获取当前线程的中断状态，这里虽然
 * 调用了threadOne.interrupt()方法，但是获取的是主线程的中断标志，因为主线程是当前线程。
 */
public class InterruptedAndIsInterrupted {

    public static void main(String[] args) {

        Thread threadOne = new Thread(() -> {
            for (; ; ) { }
        });

        threadOne.start();

        // 设置中断标志
        threadOne.interrupt();

        // 获取中断标志
        System.out.println("isInterrupted :" + threadOne.isInterrupted());

        // 获取中断标志并重置
        System.out.println("isInterrupted :" + threadOne.interrupted());

        // 获取中断标志并重置
        System.out.println("isInterrupted :" + Thread.interrupted());

        // 获取中断标志
        System.out.println("isInterrupted :" + threadOne.isInterrupted());
    }

}
