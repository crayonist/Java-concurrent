package com.labi.thread.communication;

import java.util.concurrent.LinkedBlockingDeque;

public class ProducerConsumer {

    private static final int MAX_SIZE = 5;

    private static final LinkedBlockingDeque queue = new LinkedBlockingDeque();

    static class Producer extends Thread {
        @Override
        public void run() {
            int i = 0;
            while (true) {
                synchronized (queue) {
                    while (queue.size() == MAX_SIZE) {
                        try {
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
                    queue.add("Produce");
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
                        queue.take();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("The consumer consume "+ i++ +" product.");
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

