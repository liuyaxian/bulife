package com.ruiya.util.thread.question;

import java.util.concurrent.TimeUnit;

/**
 * @desc:
 * @author: admin
 * @since: 2021/11/24 8:59
 * @history:
 */
public class test1 {
    public static void main(String[] args) {
        Phone phone = new Phone();
        new Thread( () -> {phone.sendSms();}, "A").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {phone.call();}, "B").start();
    }
}


class Phone {

    public synchronized void sendSms(){
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }

    public synchronized void call() {
        System.out.println("打电话");
    }
}