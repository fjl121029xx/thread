package com.li.thread;

public class Demo {
    public static void main(String[] args) {

        //extends Thread
        ThreadDemo td1 = new ThreadDemo();
        ThreadDemo td2 = new ThreadDemo();

        td1.setName("emoqq");
        td2.setName("008");

        td1.start();
        td2.start();

        //***********************************************//
        //implements Runnable
        RunnableDemo r = new RunnableDemo();
        Thread  t1 = new Thread(r);
        Thread  t2 = new Thread(r);

        t1.setName("龙太子");
        t2.setName("剑侠客");

        t1.start();
        t2.start();

        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();
    }
}
