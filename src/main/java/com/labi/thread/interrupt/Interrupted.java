package com.labi.thread.interrupt;

/**
 * 调用 interrupted()方法后中断标志被清除了
 */
public class Interrupted {

    public static void main(String[] args) throws InterruptedException {

        Thread threadOne = new Thread(() -> {
           // 中断标志为 true 时会退出循环，并且清除中断标志
           while (!Thread.currentThread().interrupted()) {
               // System.out.println("theadOne isInterrupted in while :" + Thread.currentThread().isInterrupted());
           }
           System.out.println("threadOne isInterrupted :" + Thread.currentThread().isInterrupted());
        });

        threadOne.start();
        Thread.sleep(1000);
        threadOne.interrupt();

        threadOne.join();

        System.out.println("main over");
    }
}
