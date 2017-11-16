package com.li.locks;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockTest {

    public static void main(String[] args) {

        final Queue3 queue3 = new Queue3();

        for (int i = 0; i < 3; i++) {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true){
                        queue3.get();
                    }
                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true){
                        queue3.put(new Random().nextInt(10000));
                    }
                }
            }).start();
        }
    }
}

class Queue3 {

    private Object data = null;

    ReadWriteLock rwl = new ReentrantReadWriteLock();

    public void get() {

        rwl.readLock().lock();

        try {

            System.out.println(Thread.currentThread().getName() + " be ready to read data! ");
            Thread.sleep((long) Math.random() * 1000);
            System.out.println(Thread.currentThread().getName() + " have read data :　" + data);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rwl.readLock().unlock();
        }
    }

    public void put(Object data) {

        rwl.writeLock().lock();

        try {

            System.out.println(Thread.currentThread().getName() + " be read to write data! ");
            Thread.sleep((long) (Math.random() * 1000));
            this.data = data;
            System.out.println(Thread.currentThread().getName() + " have write data : " + data);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {

            rwl.writeLock().unlock();
        }
    }
}
