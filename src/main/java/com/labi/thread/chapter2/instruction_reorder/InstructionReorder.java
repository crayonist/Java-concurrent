package com.labi.thread.chapter2.instruction_reorder;

/**
 * 指令重排序问题
 * 如下程序中如果共享变量ready没有加关键字volatile或者进行同步操作，在多线程下会存在共享变量内存可见性问题。
 *
 * 分析：指令重排序会造成什么影响
 *  如不考虑内存可见性的情况下，这个程序一定会输出num=4吗，其实是不一定的，
 *  由于代码(1)(2)(3)(4)的执行并没有前后依赖关系，writeThread线程(3)(4)可能被重排序为先执行(4)再执行(3)，
 *  那么如果在执行(4)之后，readThread线程可能执行了(1)操作，之后进行了(2)操作输出了num=0，此后(3)再执行。
 *
 * 总结：
 *  重排序在多线程下会导致非预期的程序执行结果，而使用了volatile修饰共享变量ready就可以避免指令重排序和内存可见性问题。
 *  写：写volatile变量时，可以确保volatile写之前的操作不会被编译器重排序到volatile写之后；
 *  读：读volatile变量时，可以确保volatile读之后的操作不会被编译器重排序到volatile读之前。
 */
public class InstructionReorder {

    private static int num = 0;
    private static boolean ready = false;

    public static void main(String[] args) throws InterruptedException {

        Thread readThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) { // 如果中断则退出
                if (ready) { // (1)
                    System.out.println("num = " + (num + num)); // (2)
                }
                System.out.println("read thread...");
            }
        });

        Thread writeThread = new Thread(() -> {
            num = 2; // (3)
            ready = true; // (4) 设置读取时的计算条件
            System.out.println("write thread set over...");
        });

        readThread.start();
        writeThread.start();

        Thread.sleep(20);
        readThread.interrupt();

        System.out.println("main exit.");
    }

}
