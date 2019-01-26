package com.labi.thread.communication;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class WaitNotify {

    private static final String share = "";

    public static void main(String[] args) {

        Thread t = new Thread(() -> {
            try {
                synchronized (share) {
                    share.wait();
                }
                System.out.println("ThreadA：Hello world");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t.start();

        new Thread(() -> {
            try {
                Thread.sleep(1000);
                System.out.println("ThreadB：Hello world");
                synchronized (share) {
                    share.notify();
                }
                // t.interrupt();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }

}
