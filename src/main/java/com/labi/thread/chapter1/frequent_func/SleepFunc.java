package com.labi.thread.chapter1.frequent_func;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程在睡眠时拥有的监视器资源不会被释放。
 */
public class SleepFunc {

    // 创建一个独占锁
    private static final Lock reentrantLock = new ReentrantLock();

    public static void main(String[] args) {

        Thread threadA = new Thread(() -> {
            // 获取独占锁
            reentrantLock.lock();
            try {
                System.out.println("threadA is in sleep.");
                Thread.sleep(5000);
                System.out.println("threadA is in awaked");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // 释放锁
                reentrantLock.unlock();
            }
        });

        Thread threadB = new Thread(() -> {
            // 获取独占锁
            reentrantLock.lock();
            try {
                System.out.println("threadB is in sleep.");
                Thread.sleep(5000);
                System.out.println("threadB is in awaked");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // 释放锁
                reentrantLock.unlock();
            }
        });

        threadA.start();
        threadB.start();

    }

}
