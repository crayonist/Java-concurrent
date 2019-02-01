package com.labi.thread.chapter3._02_threadlocalRandom;

import java.util.concurrent.ThreadLocalRandom;

public class ThreadlocalRandom {

    public static void main(String[] args) {

        ThreadLocalRandom r = ThreadLocalRandom.current();

        for (int i = 0; i < 5; i++) {
            System.out.print(r.nextInt(5) + " ");
        }

    }

}
