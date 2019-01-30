package com.labi.thread.chapter1.threadlocal;

/**
 * InheritableThreadLocal继承自ThreadLocal,其提供了一个特性，就是让子线程可以访问在父线程中设置的本地变量
 */
public class InheitableThreadlocal {

    private static ThreadLocal<String> threadLocal = new InheritableThreadLocal<>();

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
