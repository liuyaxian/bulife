package com.ruiya.lock;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @desc:
 * @author: admin
 * @since: 2021/12/10 9:00
 * @history:
 */
public class Demo2 {
//    public static void main(String[] args) {
//        Phone2 phone = new Phone2();
//
//        new Thread(()->{
//            phone.sms();
//        }, "AA").start();
////
////        new Thread(()->{
////            phone.sms();
////        }, "BB").start();
//    }
//}


    //CAS : compareAndSet 比较并交换
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(1);

        System.out.println(atomicInteger.compareAndSet(1, 2));
        System.out.println(atomicInteger.get());

        //boolean compareAndSet(int expect, int update)
        //期望值、更新值
        //如果实际值 和 我的期望值相同，那么就更新
        //如果实际值 和 我的期望值不同，那么就不更新
        System.out.println(atomicInteger.compareAndSet(2, 1));
        System.out.println(atomicInteger.get());

        //因为期望值是2020  实际值却变成了2021  所以会修改失败
        //CAS 是CPU的并发原语
//        atomicInteger.getAndIncrement(); //++操作
        System.out.println(atomicInteger.compareAndSet(1, 2));
        System.out.println(atomicInteger.get());
    }


class Phone2 {
    Lock lock = new ReentrantLock();

    public void sms() {
        lock.lock();
        try {
            System.out.println("发短信" + Thread.currentThread().getName());
            call();
        } finally {
            lock.unlock();
        }
    }

    public void call() {
        lock.lock();
        try {
            System.out.println("打电话" + Thread.currentThread().getName());
            sms();
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

}
}