package com.li.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionCommunication {

    public static void main(String[] args) {

        Business b = new Business();


        new Thread(new Runnable() {
            @Override
            public void run() {

                for (int j = 0; j < 50; j++) {

                    b.sub(j);
                }
            }
        }).start();

        for (int j = 0; j < 50; j++) {

            b.main(j);
        }

    }
}

class Business {

    private boolean flag = true;
    private Lock lock = new ReentrantLock();
    private Condition condition  = lock.newCondition();

    public void sub(int j) {
        lock.lock();
        try {
            while (!flag) {
                try {
                    //this.wait();
                    condition.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            for (int i = 0; i < 10; i++) {
                System.out.println("sub thread sequence of \t" + i + ",loop of " + j);
            }
            flag = false;
            //this.notify();
            condition.signal();
        } finally {
            lock.unlock();
        }

    }

    public void main(int j) {

        lock.lock();
        try {
            while (flag) {
                try {
                    //this.wait();
                    condition.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            for (int i = 0; i < 100; i++) {
                System.out.println("main thread sequence of \t" + i + ",loop of " + j);
            }
            flag = true;
            //this.notify();
            condition.signal();
        } finally {
            lock.unlock();
        }
    }
}
