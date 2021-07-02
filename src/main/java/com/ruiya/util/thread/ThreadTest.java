package com.ruiya.util.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/***
 *  没有线程池的测试用例
 */
public class ThreadTest {
    public static void main(String[] args) throws InterruptedException {
        long start  = System.currentTimeMillis();
        Random random = new Random();
        List<Integer> list  =  new ArrayList<>();

        for ( int i=0; i < 100000; i++){
            Thread thread = new Thread(){
                // 普通方法而已 程序中依然只有主线程这一个线程，
                // 其程序执行路径还是只有一条，还是要顺序执行
                // 并且run()方法必须是public访问权限，返回值类型为void。
                @Override
                public  void  run(){
                    list.add(random.nextInt());
                }
            };
            // 启动线程 实现了多线程运行
            thread.start();
            thread.join();
        }

        System.out.println("时间：" + (System.currentTimeMillis() - start));
        System.out.println("大小：" +  list.size());

    }
}
