package com.ruiya.lock;

/**
 * @desc:
 * @author: admin
 * @since: 2021/12/10 8:56
 * @history:
 */
public class Demo1 {
    public static void main(String[] args) {
        Phone phone = new Phone();
        new Thread(()->{
            phone.sms();
        }, "AA").start();

        new Thread(()->{
            phone.sms();
        }, "BB").start();
    }
}

class Phone{
    public  synchronized void  sms(){
        System.out.println("发短信" + Thread.currentThread().getName());
        call();
    }

    public synchronized  void call(){
        System.out.println("打电话"+Thread.currentThread().getName());
        sms();
    }
}
