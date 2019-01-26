package com.labi.thread.create_way;

public class RunableTask implements Runnable {

    @Override
    public void run() {
        System.out.println("I am a child thread.");
    }

    public static void main(String[] args) {
        RunableTask runableTask = new RunableTask();
        new Thread(runableTask).start(); // 两个线程公用一个task代码逻辑
        new Thread(runableTask).start();
    }
}
