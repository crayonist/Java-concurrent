package com.labi.thread.interrupt;

/**
 * 当线程为了等待一些特定条件的到来时，一般会调用sleep函数 wait 系列函数或者 join 函数来阻塞挂起当前线程。
 * 比如一个线程调用了Thread.sleep(1000),那么调用线程会阻塞挂起，知道1s后才会从阻塞状态恢复到激活状态；但有可能在1s内条件就已被满足，
 * 这时候可以调用该线程的interrupt方法强制sleep方法抛出InterruptedException异常而返回，线程恢复到激活状态。
 */
public class SleepInterrupt {

    public static void main(String[] args) throws InterruptedException {

        Thread threadA = new Thread(() -> {
            try {
                System.out.println("threadA is sleep 10s");
                Thread.sleep(100000);
                System.out.println("threadA is awaking");
            } catch (InterruptedException e) {
                System.out.println("threadA is interrupted while sleeping.");
                return;
            }
            System.out.println("threadA is living normally");
        });

        threadA.start();

        // 确保子线程进入休眠状态
        Thread.sleep(1000);
        // 打断子线程的休眠，让子线程从sleep 函数返回
        threadA.interrupt();

        threadA.join();

        System.out.println("main over");

    }

}
