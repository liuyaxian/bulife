package com.ruiya.javabase.thread;

public class SecondThread  implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i <100; i++) {
            System.out.println("i = " + i);
        }
    }

    public static void main(String[] args) {
        // 创建runnbale 接口实现类对象
        SecondThread secondThread = new SecondThread();
        //创建线程对象， 通过线程对象来启用线程，代理
        new Thread(secondThread).start();
        for (int i = 0; i < 100; i++) {
            System.out.println("i112313 = " + i);

        }
    }
}
