package com.ruiya.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @desc:
 * @author: admin
 * @since: 2021/11/30 10:30
 * @history:
 */
public class Demo1 {

    public static void main(String[] args) {
        //  不建议使用
        // 单个线程
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        //创建一个固定的线程池大小
        ExecutorService threadPool1 = Executors.newFixedThreadPool(5);
        // 可伸缩的， 遇强则强
        ExecutorService threadPool2 = Executors.newCachedThreadPool();


        for (int i = 0; i < 10; i++) {
            threadPool.execute(
                    () -> {   System.out.println(Thread.currentThread().getName() + " 0k" ); });

        }


    }


}
