package com.labi.thread.chapter1.create_way;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class CallableTask implements Callable {

    @Override
    public String call() {
        return "Hello Callable Thread.";
    }

    public static void main(String[] args) throws Exception {

        // 创建异步任务
        FutureTask<String> futureTask = new FutureTask<>(new CallableTask());

        // 启动线程
        new Thread(futureTask).start();

        // 等待任务执行完毕，并返回结果
        String res = futureTask.get();

        System.out.println(res);

    }

}
