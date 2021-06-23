package com.ruiya.util.thread;

/**
 * .覆写Runnable()接口实现多线程，而后同样覆写run().推荐此方式
 *     a.覆写Runnable接口实现多线程可以避免单继承局限
 *     b.当子类实现Runnable接口，此时子类和Thread的代理模式（子类负责真是业务的操作，thread负责资源调度与线程创建辅助真实业务。

**继承Thread和实现Runnable接口的区别
**a.实现Runnable接口避免多继承局限
**b.实现Runnable()可以更好的体现共享的概念

 *
 * @author admin
 */


public class MyThreadRunnable implements Runnable {

    public static int count=20;
    @Override
    public void run() {
        while(count>0) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"-当前剩余票数:"+count--);
        }
    }
    public static void main(String[] args) {
//        MyThreadRunnable Thread1=new MyThreadRunnable();
//        MyThreadRunnable mThread1=new MyThreadRunnable(Thread1,"线程1");
//        MyThreadRunnable mThread2=new MyThreadRunnable(Thread1,"线程2");
//        MyThreadRunnable mThread3=new MyThreadRunnable(Thread1,"线程3");
//        mThread1.start();
//        mThread2.start();
//        myThread3.start();
    }

    private void start() {
    }
}
