package com.li.callable$future;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.junit.Test;

import java.util.Random;
import java.util.concurrent.*;

public class CallableAndFuture {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build();
        //Common Thread Pool
        ThreadPoolExecutor pool = new ThreadPoolExecutor(5,
                200,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024),
                namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

        A a = new A();

        Callable<Object> callable = new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                int j = a.getI();
                for (int i = 0; i < 1000; i++) {
                    a.inc();
                }
                return Thread.currentThread().getName() + " -inc from " + j + " to " + a.getI();
            }
        };
        Future<Object> submit = pool.submit(callable);
        Callable<Object> callable2 = new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                int j = a.getI();
                for (int i = 0; i < 1000; i++) {
                    a.inc();
                }
                return Thread.currentThread().getName() + " -inc from " + j + " to " + a.getI();
            }
        };
        Future<Object> submit2 = pool.submit(callable2);
        Callable<Object> callable3 = new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                int j = a.getI();
                for (int i = 0; i < 1000; i++) {
                    a.inc();
                }
                return Thread.currentThread().getName() + " -inc from " + j + " to " + a.getI();
            }
        };
        Future<Object> submit3 = pool.submit(callable3);

        Object o = submit.get();
        Object o2 = submit2.get();
        Object o3 = submit3.get();

        System.out.println(o.toString());
        System.out.println(o2.toString());
        System.out.println(o3.toString());

        System.out.println(a.getI());
    }

    @Test
    public void completionServiceTest() throws InterruptedException, ExecutionException {

        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build();
        //Common Thread Pool
        ThreadPoolExecutor pool = new ThreadPoolExecutor(5,
                200,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024),
                namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

        CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(pool);

        for (int i   = 0; i < 10; i++) {
            int seq = 1;
            completionService.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    Thread.sleep(new Random().nextInt(5000));
                    return seq;
                }
            });
        }

        for (int i = 0; i < 10; i++) {

            System.out.println(completionService.take().get());
        }
    }
}

class A {
    private int i = 0;

    public synchronized void inc() {

        i++;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }
}

