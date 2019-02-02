package com.labi.thread.chapter4._01_atomic;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 统计0的个数
 * 在没有原子类的情况下，实现计数器需要使用一定的同步措施，比如使用synchronized等，但这些都是阻塞算法，对性能有一定损耗；
 * 而原子操作类都是用CAS非阻塞算法，性能更好。
 * 但在高并发下AtomicLong还会存在性能问题。
 */
public class Atomic {

    private static final AtomicLong atomicLong = new AtomicLong();

    private static Integer[] arrayOne = new Integer[] {0, 1, 2, 3, 0, 5, 6, 0, 56, 0};
    private static Integer[] arrayTwo = new Integer[] {10, 1, 2, 3, 0, 5, 6, 0, 56, 0};

    public static void main(String[] args) throws InterruptedException {

        Thread threadOne = new Thread(() -> count0(arrayOne));

        Thread threadTwo = new Thread(() -> count0(arrayTwo));

        threadOne.start();
        threadTwo.start();

        threadOne.join();
        threadTwo.join();

        System.out.println("count 0: " + atomicLong.get());
    }

    private static void count0(Integer[] array) {
        Arrays.stream(array)
                .filter(i -> i == 0)
                .forEach(i -> atomicLong.incrementAndGet());
    }

}
