package com.labi.thread.chapter3._02_threadlocalRandom;

import java.util.Random;

public class RandomDemo {

    public static void main(String[] args) {
        Random r = new Random();

        for (int i = 0; i < 5; i++) {
            System.out.print(r.nextInt(5) + " ");
        }
    }

}
