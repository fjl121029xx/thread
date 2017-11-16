package com.li.threadlocal;

import java.util.Random;

public class ThreadLocalTest {

    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>();
    //private static ThreadLocal<MyThreadScopeData> m = new ThreadLocal<MyThreadScopeData>();

    public static void main(String[] args) {

        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {


                    int data = new Random().nextInt();
                    System.out.println(Thread.currentThread().getName() + " has put data : " + data);

                    threadLocal.set(data);

                    /*MyThreadScopeData value = new MyThreadScopeData("zhangsan" + data, data);
                    m.set(value);*/

                    MyThreadScopeData m = MyThreadScopeData.getThreadInstance();
                    m.setName("zhangsan" + data);
                    m.setAge(data);
                    new A().get();
                    new B().get();
                }
            }).start();

        }
    }

    static class A {

        public void get() {
            Integer data = threadLocal.get();
            System.out.println("A from " + Thread.currentThread().getName() + " get data : " + data);

            MyThreadScopeData myThreadScopeData = MyThreadScopeData.getThreadInstance();
            System.out.println("A from " + Thread.currentThread().getName() + " get value : " + myThreadScopeData);
        }
    }

    static class B {

        public void get() {
            Integer data = threadLocal.get();
            System.out.println("B from " + Thread.currentThread().getName() + " get data : " + data);

            MyThreadScopeData myThreadScopeData = MyThreadScopeData.getThreadInstance();
            System.out.println("B from " + Thread.currentThread().getName() + " get value : " + myThreadScopeData);
        }
    }
}

class MyThreadScopeData {

    private String name;
    private int age;

    //private static MyThreadScopeData instance = null;
    /**
     * TL
     */
    private static final ThreadLocal<MyThreadScopeData> TL = new ThreadLocal();

    private MyThreadScopeData() {
    }

    public static MyThreadScopeData getThreadInstance() {

        MyThreadScopeData instance = TL.get();
        if (instance == null) {
            instance = new MyThreadScopeData();
            TL.set(instance);
        }
        return instance;
    }

    public void remove(){
        TL.remove();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
