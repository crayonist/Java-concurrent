package com.labi.thread.threadlocal;

public class ThreadLocalNotforExtend {

    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {

        threadLocal.set("hello threadLocal");

        Thread thread = new Thread(() -> {
            // 子线程输出线程变量的值
            System.out.println("thread：" + threadLocal.get());
        });

        thread.start();

        // 主线程输出线程变量的值
        System.out.println("main: " + threadLocal.get());
    }

}
