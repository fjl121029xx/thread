package com.li.semaphore;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * Samphore可以维护当前访问自身的线程个数,并提供了同步机制.
 * 使用Samphore可以控制同时访问资源的线程个数.
 * 单个信号量的semphore对象可以实现互斥锁的功能,并且可以是由一个线程获得了锁,再有另一个线程释放锁,这可应用于死锁恢复的一些场合
 * semahores实现的功能就类似厕所有5个坑
 */
public class SemaphoreTest {

    public static void main(String[] args) {

        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build();
        //Common Thread Pool
        ThreadPoolExecutor pool = new ThreadPoolExecutor(5,
                200,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024),
                namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

        final Semaphore semaphore = new Semaphore(3);//信号灯
        for (int i = 0; i < 10; i++) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();//拿灯
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                    System.out.println("线程" + Thread.currentThread().getName() +
                            "进入，当前已有" + (3 - semaphore.availablePermits()) + "个并发");
                    try {
                        Thread.sleep((long) (Math.random() * 100));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("线程" + Thread.currentThread().getName() +
                            "即将离开");
                    semaphore.release();//释放灯
                    //下面代码有时候执行不准确，因为其没有和上面的代码合成原子单元
                    System.out.println("线程" + Thread.currentThread().getName() +
                            "已离开，当前已有" + (3 - semaphore.availablePermits()) + "个并发");
                }
            };
            pool.execute(runnable);
        }
    }
}
