package com.ruiya.pool;

import java.util.concurrent.*;

/**
 * @desc:
 * @author: admin
 * @since: 2021/11/30 10:50
 * @history:
 */
public class Demo2 {

        public static void main(String[] args) {

            int max = Runtime.getRuntime().availableProcessors();

            ExecutorService threadPool = new ThreadPoolExecutor(
                    2,
                    max,
                    3,
                    TimeUnit.SECONDS,
                    new LinkedBlockingQueue<>(3),
                    Executors.defaultThreadFactory(),
                    new ThreadPoolExecutor.AbortPolicy()
            );

            try{
                for (int i = 1; i < 40; i++) {
                    threadPool.execute(
                            () -> {   System.out.println(Thread.currentThread().getName() + " 0k" ); });

                }
            }catch (Exception e){

            }finally {
                threadPool.shutdown();
            }

        }

}
