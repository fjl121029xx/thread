package com.li.thread;

import javax.sound.midi.SoundbankResource;

public class RunnableDemo implements Runnable{

    @Override
    public void run() {

        for (int i = 0; i <= 100 ; i++ ){
            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName()+"\t-->"+i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
