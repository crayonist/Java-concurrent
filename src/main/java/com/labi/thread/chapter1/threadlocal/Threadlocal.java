package com.labi.thread.chapter1.threadlocal;

public class Threadlocal {

    private static ThreadLocal<String> localVariable = new ThreadLocal<>();

    public static void main(String[] args) {

        Thread threadOne = new Thread(() -> {
            // 设置线程One中本地变量localVariable的值
            // 通过set方法设置了localVariable的值，这其实设置的是threadOne本地内存中的一个副本，这个副本线程Two是访问不了的
            localVariable.set("threadOne local variable");
            print("threadOne");
            System.out.println("threadOne:" + localVariable.get());
        });

        Thread threadTwo = new Thread(() -> {
            localVariable.set("threadTwo local variable");
            print("threadTwo");
            System.out.println("threadTwo:" + localVariable.get());
        });

        threadOne.start();
        threadTwo.start();
    }

    private static void print(String str) {
        // 打印当前线程本地内存中localVariable变量的值
        System.out.println(str + ":" + localVariable.get());
        // 清除当前线程本地内存中的localVariable变量
        localVariable.remove();
    }

}
