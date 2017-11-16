package com.li.waitandnotify;

public class TraditionThreadCommunication {

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

    public synchronized void sub(int j) {
        while (!flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < 10; i++) {
            System.out.println("sub thread sequence of \t" + i + ",loop of " + j);
        }
        flag = false;
        this.notify();
    }

    public synchronized void main(int j) {
        while (flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < 100; i++) {
            System.out.println("main thread sequence of \t" + i + ",loop of " + j);
        }
        flag = true;
        this.notify();
    }
}
