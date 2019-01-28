package com.labi.thread.frequent_func;

public class JoinFunc {

    public static void main(String[] args) throws InterruptedException {

        Thread threadOne = new Thread(() -> {
            System.out.println("ThreadOne begin run");
            for (;;) { }
        });

        // 获取主线程
        Thread mainThread = Thread.currentThread();

        Thread threadTwo = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 中断主线程
            mainThread.interrupt();
        });

        threadOne.start();

        threadTwo.start();

        try { // 等待线程one执行
            threadOne.join();
        }catch (InterruptedException e ) {
            System.out.println("main thread:" + e);
        }
    }

}
