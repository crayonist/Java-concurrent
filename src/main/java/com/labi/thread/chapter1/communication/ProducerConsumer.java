package com.labi.thread.chapter1.communication;

import java.util.concurrent.LinkedBlockingDeque;

/**
 * Simple Producer-Consumer Model
 */
public class ProducerConsumer {

    private static final int MAX_SIZE = 5;

    private static final LinkedBlockingDeque queue = new LinkedBlockingDeque();

    static class Producer extends Thread {
        @Override
        public void run() {
            int i = 0;
            while (true) {
                synchronized (queue) {
                    // 消费队列满，则等待队列空闲
                    while (queue.size() == MAX_SIZE) {
                        try {
                            // 挂起当前线程，并释放通过同步块获取的queue上的锁，让消费者线程可以获取该锁，然后获取队列里面的元素
                            // 注意：当前线程调用共享变量的wait()后只会释放当前共享变量上的锁，如果当前线程还持有其他共享变量的锁，这些锁是不会被释放的。
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    queue.add("Product");
                    System.out.println("The producer produced "+ i++ +" product.");
                    queue.notify();
                }
            }
        }
    }

    static class Consumer extends Thread {
        @Override
        public void run() {
            int i = 0;
            while (true) {
                synchronized (queue) {
                    while (queue.size() == 0) {
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        // 消费队列元素
                        queue.take();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("The consumer consume "+ i++ +" product.");
                    // 消费完通知唤醒生产者线程
                    queue.notify();
                }
            }
        }
    }

    public static void main(String[] args) {
        Producer p = new Producer();
        p.start();
        Consumer c = new Consumer();
        c.start();
    }

}

