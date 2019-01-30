package com.labi.thread.chapter1.communication;

/**
 * notify:只能唤醒一个共享变量wait的线程
 * notifyAll:唤醒所有共享变量wait等待集合里面的所有线程
 * 注：在共享变量上调用 notifyAll()方法只会唤醒调用这个方法前调用了wait系列函数而被放入共享变量等待集合里面的线程。
 * 如果调用 notifyAll()方法后，一个线程调用了该共享变量的wait()方法而被放入阻塞集合，则该线程是不会被唤醒的。
 */
public class NotifyNotifyAll {

    private static volatile Object resourceA = new Object();

    public static void main(String[] args) throws InterruptedException {

        Thread threadA = new Thread(() -> {
            synchronized (resourceA) {
                System.out.println("threadA get resourceA lock");
                try {
                    System.out.println("threadA begin wait");
                    resourceA.wait();
                    System.out.println("threadA end wait");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread threadB = new Thread(() -> {
            synchronized (resourceA) {
                System.out.println("threadB get resourceA lock");
                try {
                    System.out.println("threadB begin wait");
                    resourceA.wait();
                    System.out.println("threadB end wait");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread threadC = new Thread(() -> {
            synchronized (resourceA) {
                System.out.println("threadC begin notify");
                // resourceA.notify();
                resourceA.notifyAll();
            }
        });

        threadA.start();
        threadB.start();
        Thread.sleep(1000);
        threadC.start();

        threadA.join();
        threadB.join();
        threadC.join();

        System.out.println("main over");
    }
}
