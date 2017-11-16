package com.li.synchronized_;

public class TraditionalThreadSynchronized {

    public static void main(String[] args) {

        Outputer op = new Outputer();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
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
                while (true){
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

    static class Outputer{

        public void output(String name){

            int len = name.length();
            synchronized (Outputer.class) {
                for ( char c : name.toCharArray()) {

                    System.out.print(c);
                }
                System.out.println();
            }
        }
        public synchronized void output2(String name){

            int len = name.length();
            for ( char c : name.toCharArray()) {

                System.out.print(c);
            }
            System.out.println();
        }
        public static synchronized void output3(String name){

            int len = name.length();
            for ( char c : name.toCharArray()) {

                System.out.print(c);
            }
            System.out.println();
        }
    }
}
