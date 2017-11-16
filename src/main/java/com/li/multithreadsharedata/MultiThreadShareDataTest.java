package com.li.multithreadsharedata;

public class MultiThreadShareDataTest {

    private static int i;

    public static void main(String[] args) {

        for (int i = 0; i < 2; i++) {

            new Thread(new Inc()).start();

            new Thread(new Dec()).start();
        }
    }

    public synchronized static void inc() {
        i++;
        System.out.println(Thread.currentThread().getName() + " -inc: " + i);
    }

    public synchronized static void dec() {
        i--;
        System.out.println(Thread.currentThread().getName() + " -dec: " + i);
    }

    static class Inc implements Runnable{

        @Override
        public void run() {

            for (int j = 0; j < 100; j++) {
                inc();
            }
        }
    }
    
    static class Dec implements Runnable{

        @Override
        public void run() {

            for (int j = 0; j < 100; j++) {
                dec();
            }
        }
    }
}
