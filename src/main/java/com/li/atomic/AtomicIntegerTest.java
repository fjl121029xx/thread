package com.li.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {

    public static void main(String[] args) {

        AtomicIntegerDemo aideo = new AtomicIntegerDemo();

        new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < 10; i++) {

                    aideo.inc();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < 10; i++) {

                    aideo.dec();
                }
            }
        }).start();
    }

    static class AtomicIntegerDemo {

        AtomicInteger ai = new AtomicInteger(0);

        public void inc() {

            ai.incrementAndGet();
            System.out.println(Thread.currentThread().getName() + " -inc: " + ai.get());
        }

        public void dec() {

            ai.decrementAndGet();
            System.out.println(Thread.currentThread().getName() + " -dec: " + ai.get());
        }
    }
}
