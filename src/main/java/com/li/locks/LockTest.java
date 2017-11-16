package com.li.locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {

    public static void main(String[] args) {

        Outputer op = new Outputer();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(500);
                        op.output("emoqq");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(500);
                        op.output2("guwenlong");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    static class Outputer {
        /**
         * LOOK
         */
        private static final Lock LOCK = new ReentrantLock();

        public void output(String name) {

            try {
                LOCK.lock();
                int len = name.length();
                for (char c : name.toCharArray()) {

                    System.out.print(c);
                }
                System.out.println();
            } finally {

                LOCK.unlock();
            }
        }

        public void output2(String name) {

            try {
                LOCK.lock();
                int len = name.length();
                for (char c : name.toCharArray()) {

                    System.out.print(c);
                }
                System.out.println();
            } finally {

                LOCK.unlock();
            }
        }
    }
}
