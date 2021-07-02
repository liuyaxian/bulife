package com.ruiya.util.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/***
 * 生命周期
 1.继承Thread类实现多线程
 run()为线程类的核心方法，相当于主线程的main方法，是每个线程的入口
  	a.一个线程调用 两次start()方法将会抛出线程状态异常，也就是的start()只可以被调用一次 
 b.native生明的方法只有方法名，没有方法体。是本地方法，不是抽象方法，而是调用c语言方法
 registerNative()方法包含了所有与线程相关的操作系统方法
    c.run()方法是由jvm创建完本地操作系统级线程后回调的方法，不可以手动调用（否则就是普通方法）
 */
public class MyThread  extends  Thread{
    public MyThread(){

    }
    @Override
    public  void run(){
        for(int i=0;i<10000;i++) {
            System.out.println(Thread.currentThread()+":"+i);
        }

    }

//    public static void main(String[] args) {
//        MyThread mThread1=new MyThread();
//        MyThread mThread2=new MyThread();
//        MyThread myThread3=new MyThread();
//        mThread1.start();
//        mThread2.start();
//        myThread3.start(); //
//        myThread3.run(); // 方法级别调用
//    }
// 100001 个线程
    public static void main(String[] args) throws InterruptedException {
        long start  = System.currentTimeMillis();
        Random random = new Random();
        List<Integer> list  =  new ArrayList<>();
        list.stream();
        for ( int i=0; i < 100000; i++){
            Thread thread = new Thread(){
                @Override
                public  void  run(){
                    list.add(random.nextInt());
                }
            };
            thread.start();
            thread.join();
        }
        System.out.println("时间：" + (System.currentTimeMillis() - start));
        System.out.println("大小：" +  list.size());

    }
}
