package com.labi.thread.chapter4._01_atomic;

import java.util.concurrent.atomic.AtomicLong;

public class AtomicLongDemo {

    public static void main(String[] args) {
        // incrementAndGet(2);
        // decrementAndGet(2);
        // getAndIncrement(2);
        long l = getAndDecrement(2);
        System.out.println(l);
    }

    /**
     * 相当于++i操作，value为原始值+1
     * @param initialValue
     * @return 递增后的值
     */
    private static final long incrementAndGet(long initialValue) {
        AtomicLong atomic = new AtomicLong(initialValue);
        return atomic.incrementAndGet();
    }

    /**
     * 相当于--i操作，value为原始值-1
     * @param initialValue
     * @return 递减后的值
     */
    private static final long decrementAndGet(long initialValue) {
        AtomicLong atomic = new AtomicLong(initialValue);
        return atomic.decrementAndGet();
    }

    /**
     * 相当于i++操作，get之后再increment
     * 原子性设置value值为原始值+1
     * @param initialValue
     * @return 原始值
     */
    private static final long getAndIncrement(long initialValue) {
        AtomicLong atomic = new AtomicLong(initialValue);
        return atomic.getAndIncrement();
    }

    /**
     * 相当于i--操作，get之后在decrement
     * 原子性设置value值为原始值-1
     * @param initialValue
     * @return 原始值
     */
    private static final long getAndDecrement(long initialValue) {
        AtomicLong atomic = new AtomicLong(initialValue);
        return atomic.getAndDecrement();
    }

}
