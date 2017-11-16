package com.li.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreeConditionCommunication {

    public static void main(String[] args) {

        A a = new A();

        new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < 50; i++) {
                    a.three(i);
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < 50; i++) {
                    a.two(i);
                }
            }
        }).start();

        for (int i = 0; i < 50; i++) {
            a.one(i);
        }
    }

}

class A {

    private Lock lock = new ReentrantLock();

    private Condition oneCondition = lock.newCondition();
    private Condition twoCondition = lock.newCondition();
    private Condition threeCondition = lock.newCondition();

    public void one(int i) {
        try {
            lock.lock();
            int j = 0;

            Thread.currentThread().setName("one");
            for (; j < 100; j++) {
                System.out.println(Thread.currentThread().getName() + " is looping of " + j + " under of " + i);

                if (j == 99){
                    try {
                        twoCondition.signal();
                        oneCondition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        } finally {
            lock.unlock();
        }
    }

    public void two(int i) {
        try {
            lock.lock();

            int j = 0;
            Thread.currentThread().setName("two");
            for (; j < 50; j++) {

                System.out.println(Thread.currentThread().getName() + " is looping of " + j + " under of " + i);
                if (j == 49){
                    try {
                        threeCondition.signal();
                        twoCondition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        } finally {
            lock.unlock();
        }
    }

    public void three(int i) {
        try {
            lock.lock();
            int j = 0;
            Thread.currentThread().setName("three");
            for (; j < 10; j++) {

                System.out.println(Thread.currentThread().getName() + " is looping of " + j + " under of " + i);
                if (j == 9){
                    try {
                        oneCondition.signal();
                        threeCondition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        } finally {
            lock.unlock();
        }
    }

}
