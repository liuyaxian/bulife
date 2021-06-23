package com.ruiya.util.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * 通过线程池启动多线程
 *  通过Executor 的工具类可以创建三种类型的普通线程池：
 *
 *
 */
public class MyThreadExecutor {
    // FixThreadPool(int n); 固定大小的线程池
    // 使用于为了满足资源管理需求而需要限制当前线程数量的场合。使用于负载比较重的服务器。
    public static void main(String[] args) {
        ExecutorService ex = Executors.newFixedThreadPool(10);
        long startTime = System.currentTimeMillis();
        for ( int i =0 ; i <10 ; i ++) {
            ex.submit(new Runnable() {
                @Override
                public void run() {
                    for(int j=0;j<10000;j++) {

                    }
                }
            });
        }
        System.out.println("执行时间："+(System.currentTimeMillis() - startTime)/ (60*60));
        ex.shutdown();
    }
}
