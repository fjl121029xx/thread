package com.li.exchanger;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * 用于实现两个人之间的数据交换,每个人在完成一定的事务后想与对方交换数据
 * 第一个拿出数据的人将一直等待第二个人拿着数据到来时,才能彼此交换数据
 */
public class ExchangerTest {

    public static void main(String[] args) {

        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build();
        //Common Thread Pool
        ThreadPoolExecutor pool = new ThreadPoolExecutor(5,
                200,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024),
                namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

        final Exchanger exchanger = new Exchanger();
        final Exchanger exchanger2 = new Exchanger();
        final Exchanger exchanger3 = new Exchanger();

        pool.execute(new Runnable() {
            @Override
            public void run() {
                try {

                    Thread.currentThread().setName("小明");

                    String data1 = "小明";
                    System.out.println("线程" + Thread.currentThread().getName() +
                            "正在把数据" + data1 + "换出去");
                    Thread.sleep((long) (Math.random() * 10000));

                    String data2 = (String) exchanger.exchange(data1);//lhm


                    String data3 = (String) exchanger3.exchange(data1);//lham

                    System.out.println("线程" + Thread.currentThread().getName() +
                            "换回的数据为" + data3);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        pool.execute(new Runnable() {
            @Override
            public void run() {
                try {

                    Thread.currentThread().setName("小红");

                    String data1 = "小红";
                    System.out.println("线程" + Thread.currentThread().getName() +
                            "正在把数据" + data1 + "换出去");
                    Thread.sleep((long) (Math.random() * 10000));
                    String data2 = (String) exchanger.exchange(data1);//zxx

                    String data3 = (String) exchanger2.exchange(data1);//lham

                    System.out.println("线程" + Thread.currentThread().getName() +
                            "换回的数据为" + data2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        pool.execute(new Runnable() {
            @Override
            public void run() {
                try {

                    Thread.currentThread().setName("小花");

                    String data1 = "小花";
                    System.out.println("线程" + Thread.currentThread().getName() +
                            "正在把数据" + data1 + "换出去");
                    Thread.sleep((long) (Math.random() * 10000));
                    String data2 = (String) exchanger2.exchange(data1);//lhm

                    String data3 = (String) exchanger3.exchange(data1);//zxx

                    System.out.println("线程" + Thread.currentThread().getName() +
                            "换回的数据为" + data2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
