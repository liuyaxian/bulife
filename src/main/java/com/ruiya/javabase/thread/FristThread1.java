package com.ruiya.javabase.thread;

//  创建线程第一种方式：   继承 Thread  重新run（）方法， 调用start开启线程
//总结： 注意，线程开启不一定立即执行， 由cpu调度决定
public class FristThread1 extends Thread{
    
    @Override
    public void run(){
        for (int i = 0; i < 10; i++) {
            System.out.println("runi = " + i);
        }
    }

    public static void main(String[] args) {
        // 创建线程对象
        FristThread1 fristThread1 = new FristThread1();
        // 调用start（） 方法开启线程， 两个线程同时执行
        fristThread1.start();
        for (int i = 0; i < 10 ; i++) {
            System.out.println("main = " + i );
        }
        System.out.println("fristThread1 = " +  Runtime.getRuntime().availableProcessors());
    }

}
