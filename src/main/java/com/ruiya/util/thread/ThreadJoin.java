package com.ruiya.util.thread;

/**
 * join()方法是Thread类中的一个方法，
 * 该方法的定义是等待该线程终止。其实就是join()方法将挂起调用线程的执行，
 * 直到被调用的对象完成它的执行。
 */
public class ThreadJoin {

    /**
     * 现在有T1、T2、T3三个线程，你怎样保证T2在T1执行完后执行，
     * T3在T2执行完后执行？这个问题是网上很热门的面试题目
     * （这里除了用join之外还有很多其他方法能够实现，只是使用join是最简单的方案），
     * 下面是实现的代码:
     */

    public static void main(String[] args) {
        //初始化线程t1,由于后续有匿名内部类调用这个对象,需要用final修饰
        final Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                System.out.println("t1 is running");
            }
        });
        //初始化线程t2,由于后续有匿名内部类调用这个对象,需要用final修饰
        final Thread t2 = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    //t1调用join方法,t2会等待t1运行完之后才会开始执行后续代码
                    t1.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("t2 is running");
                }
            }
        });
        //初始化线程t3
        Thread t3 = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    //t2调用join方法,t3会等待t2运行完之后才会开始执行后续代码
                    t2.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("t3 is running");
                }
            }
        });
        //依次启动3个线程
        t1.start();
        t2.start();
        t3.start();
    }
}
