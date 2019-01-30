package com.labi.thread.chapter1.create_way;

public class MyThread extends Thread {

    @Override
    public void run() {

        System.out.println("I am a child thread.");

    }

    public static void main(String[] args) {

        // 创建线程
        Thread t = new MyThread();

        // 启动线程
        t.start(); // 调用start()方法后线程并没有马上执行而是处于就绪状态，等待ＣＰＵ分配资源获取后才会真正处于运行状态，run()方法执行完毕，该线程处于终止状态

    }
}
