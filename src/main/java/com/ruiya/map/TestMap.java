package com.ruiya.map;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @desc:
 * @author: admin
 * @since: 2021/12/1 10:38
 * @history:
 */
public class TestMap {

    public void get(){
        Map<String, String> map  = new HashMap<>();
        map.put("f","2");
        map.put("wew","2323");
        map.entrySet().forEach(stringStringEntry -> {stringStringEntry.getValue(); stringStringEntry.getKey();});
    }


    public void setCountDownLatch(){
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


    public void getCyclicBarrier(){
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("召唤神龙");
        });

        for (int i = 1; i <= 3; i++) {
//                子线程
           final int finalI = i;
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"收集了第"+finalI+"颗龙珠");
                try {
                    cyclicBarrier.await();//加法计数等待
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }

            }).start();
        }
    }

    public void getSemaphore(){
//        线程数量，停车位，限流
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i <=6 ; i++) {
            new Thread(()->{
//                acquire()得到
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"抢到车位");
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName()+"离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            }).start();
        }
    }

}
