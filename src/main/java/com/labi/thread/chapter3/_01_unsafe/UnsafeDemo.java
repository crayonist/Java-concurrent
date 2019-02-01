package com.labi.thread.chapter3._01_unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnsafeDemo {

    // private static final Unsafe unsafe = Unsafe.getUnsafe(); // AppClassLoader加载不了Unsafe类，需要Boostrap类去加载Unsafe类

    private static Unsafe unsafe;

    // 记录变量state在类UnsafeDemo中的偏移值
    private static long stateOffset;

    private volatile long state = 0;

    static {
        try {
            // 使用反射获取Unsafe的成员变量theUnsafe
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            // 设置为可存取
            field.setAccessible(true);
            // 获取该变量的值
            unsafe = (Unsafe) field.get(null);
            // 获取state变量在类UnsafeDemo中的偏移值
            stateOffset = unsafe.objectFieldOffset(UnsafeDemo.class.getDeclaredField("state"));
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            throw new Error(e);
        }
    }

    public static void main(String[] args) {
        UnsafeDemo unsafeDemo = new UnsafeDemo();
        // 比较unsafeDemo中偏移量为stateOffset的变量的值是否与expect=0相等，相等则更新为state=1，然后返回true，否则返回false
        boolean success = unsafe.compareAndSwapInt(unsafeDemo, stateOffset, 0, 1);
        System.out.println(success);
    }

}
