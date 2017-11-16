package com.li.threadpool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

public class ThreadPoolTest {

    public static void main(String[] args) {

        //ExecutorService executorService = Executors.newFixedThreadPool(3);
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build();
        //Common Thread Pool
        ThreadPoolExecutor pool = new ThreadPoolExecutor(5,
                200,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024),
                namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

        for (int j = 0; j < 5; j++) {

            pool.execute(() -> {
                for (int i = 0; i < 10; i++) {
                    //System.out.println(Thread.currentThread().getName() + " is looping of " + i);
                }
            });

        }
        pool.shutdown();


        Executors.newScheduledThreadPool(3).schedule(
                () -> {

                },
                10,
                TimeUnit.SECONDS);

        Executors.newScheduledThreadPool(3).scheduleAtFixedRate(
                ()->{
                    System.out.println("bomm");
                },
                10,
                2,
                TimeUnit.SECONDS

        );
    }
}
