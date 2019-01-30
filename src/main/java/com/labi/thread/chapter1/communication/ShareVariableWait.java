package com.labi.thread.chapter1.communication;

/**
 * 当前线程调用共享变量的wait()后只会释放当前共享变量上的锁，如果当前线程还持有其他共享变量的锁，这些锁是不会被释放的。
 * 看如下例子：
 *    线程Ａ挂起后并没有释放获取大到的ResourceB上的锁，所以线程Ｂ尝试获取resourceB上的锁会被阻塞。
 */
public class ShareVariableWait {

    private static volatile Object resourceA = new Object();
    private static volatile Object resourceB = new Object();

    public static void main(String[] args) throws InterruptedException {

        Thread threadA = new Thread(() -> {
            try {
                // 获取resourceA资源的监视器锁
                synchronized (resourceA) {
                    System.out.println("threadA get resourceA lock");
                    // 获取resourceB共享资源的监视器锁
                    synchronized (resourceB) {
                        System.out.println("threadA get resourceB lock");

                        // 线程A阻塞，并释放获取到的resourceA的锁
                        System.out.println("threadA release resourceA lock");
                        resourceA.wait();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread threadB = new Thread(() -> {
            try {
                Thread.sleep(1000);
                // 获取resourceA资源的监视器锁
                synchronized (resourceA) {
                    System.out.println("threadB get resourceA lock");
                    System.out.println("threadB try to get resourceB lock...");
                    // 获取resourceB共享资源的监视器锁
                    synchronized (resourceB) {
                        System.out.println("threadB get resourceB lock");

                        // 线程B阻塞，并释放获取到的resourceA的锁
                        System.out.println("threadB release resourceA lock");
                        resourceA.wait();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // 启动线程
        threadA.start();
        threadB.start();

        // 等待两个线程结束
        threadA.join();
        threadB.join();

        System.out.println("main over");

    }

}
