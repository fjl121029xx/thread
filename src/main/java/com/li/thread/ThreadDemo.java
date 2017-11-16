package com.li.thread;

public class ThreadDemo extends Thread {
    @Override
    public void run() {

        for (int i = 0 ;i <= 100; i++){
            try {
                Thread.sleep(500);
                System.out.println(Thread.currentThread().getName()+"\t-->"+i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
