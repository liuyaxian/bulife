package com.ruiya.util.thread;

import java.util.concurrent.CountDownLatch;

/**
 * @desc:
 * @author: admin
 * @since: 2021/11/25 17:30
 * @history:
 */
public class CountDownLatchDemo {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i < 5; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"==>Go Out");
                countDownLatch.countDown(); //每个线程都数量-1
            },String.valueOf(i)).start();
        }
        try {
            countDownLatch.await();// 等待计数器归零，然后向下执行
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("close door");
    }
}