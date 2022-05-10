package com.ruiya.util.thread;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.*;

public class ThreasPoolDemo {
    public static void main(String[] args) {
        // 线程数的数量越多越好 错误

        /**
         *      return new ThreadPoolExecutor(0, Integer.MAX_VALUE,   核心线程数0， 最大线程数 无穷大
         *                                       60L, TimeUnit.SECONDS,
         *                                       new SynchronousQueue<Runnable>());  同步队列， 节点只有一个， 只能存一个任务
         */
        // 多少任务多少个线程， (任务执行时间短时线程复用， 一人做多个项目)
        // 快   不会出现内存溢出  只有一个节点， 内存占用少
        ExecutorService executorService1 = Executors.newCachedThreadPool();

        /**
         *        return new ThreadPoolExecutor(nThreads, nThreads,    核心线程数10 ,  最大线程数 1O
         *                                       0L, TimeUnit.MILLISECONDS,
         *                                       new LinkedBlockingQueue<Runnable>()); 队列比较长
         *                                       队列中排序
         *  线程数为 10 ，
         */
        // 慢   OOM 会出现内存溢出（超级计算机硬件有关）  核心线程数的多少
        ExecutorService executorService2 = Executors.newFixedThreadPool(100);
        /***
         *         return new FinalizableDelegatedExecutorService
         *             (new ThreadPoolExecutor(1, 1,
         *                                     0L, TimeUnit.MILLISECONDS,
         *                                     new LinkedBlockingQueue<Runnable>()));
         */
        // 最慢的 会出现内存溢出  单个线程   是ThreadPoolExecutor 的单一版本   个体户
        ExecutorService executorService3 = Executors.newSingleThreadExecutor();

        // 阿里巴巴推荐使用自定义线程池   非核心  20 -10 = 10 个
        // 提交优先级 ：核心线程 >
        // 执行优先级 ： 那里有任务就在哪里执行
        ThreadPoolExecutor threadPoolExecutor4 = new ThreadPoolExecutor(12, 12,
                0L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(4)
//                , new RejectedExecutionHandler() {
//                    @Override
//                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
//                        for (int i =1; i<= 10000000; i++){
//                            System.out.println("当前线程： " + Thread.currentThread() + "执行i： "+ i);
//                        }
//                    }
//                }
        );

        for (int i =1; i<= 10000000; i++){
            threadPoolExecutor4.execute(new Runnable() {
                @Override
                public void run() {
                    for (int i1 = 0; i1 < 100000; i1++) {
                        // 业务逻辑
                        InetAddress byName = null;
                        try {
                            byName = InetAddress.getByName("www.luckywhb.com");
                        } catch (UnknownHostException e) {
                            e.printStackTrace();
                        }
                        System.out.println("byName1.getAddress() = " + byName.getAddress());
                        // 规范的名字
                        System.out.println("byName1.getCanonicalHostName() = " + byName.getCanonicalHostName());
                        // ip
                        System.out.println("byName1.getHostAddress() = " + byName.getHostAddress());
                        // 域名 电脑名
                        System.out.println("byName1.getHostName() = " + byName.getHostName());
                        System.out.println("当前线程： " + Thread.currentThread() + "执行i： "+ i1);

                    }

                }
            });
      }
    }

}

// 队列， 存储  内存开辟太多，占用的资源也多 LinkedBlockingQueue
// 程序： 数据结构+ 算法

//    public ThreadPoolExecutor
////    (int corePoolSize,  核心线程数
//      int maximumPoolSize,  最大线程数
//      long keepAliveTime, 时间
//      TimeUnit unit, 时间单位
//      BlockingQueue<Runnable> workQueue,  队列
//      ThreadFactory threadFactory,  线程工厂
//      RejectedExecutionHandler handler) {  拒绝策略
//


class  MyTask implements  Runnable{
    int i =0;
    public  MyTask(){
        this.i =i;
    }
    @Override
    public void run(){
        System.out.println(Thread.currentThread().getName() +"做第" + i + "个项目");
        try{
            // 业务逻辑
            InetAddress byName = InetAddress.getByName("www.luckywhb.com");
            System.out.println("byName1.getAddress() = " + byName.getAddress());
            // 规范的名字
            System.out.println("byName1.getCanonicalHostName() = " + byName.getCanonicalHostName());
            // ip
            System.out.println("byName1.getHostAddress() = " + byName.getHostAddress());
            // 域名 电脑名
            System.out.println("byName1.getHostName() = " + byName.getHostName());


        } catch (Exception e){
            e.printStackTrace();
        }
    }
}