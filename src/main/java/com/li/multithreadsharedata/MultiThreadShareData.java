package com.li.multithreadsharedata;

public class MultiThreadShareData {

    public static void main(String[] args) {

        final ShareDate sd = new ShareDate();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){

                    sd.increase();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){

                    sd.decrease();
                }
            }
        }).start();
    }
}

class ShareDate {

    private int j = 0;
    /*private int count = 100;

    @Override
    public void run() {
        while (count > 0) {
            count--;
            System.out.println(Thread.currentThread().getName() + "\t" + count);
        }
    }*/

    public synchronized void increase() {
        j++;
        System.out.println(Thread.currentThread().getName() + "\t" + j);
    }

    public synchronized void decrease() {
        j--;
        System.out.println(Thread.currentThread().getName() + "\t" + j);
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

}