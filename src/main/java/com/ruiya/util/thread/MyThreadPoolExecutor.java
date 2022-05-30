package com.ruiya.util.thread;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.util.concurrent.*;

/**
 * @desc:
 * @author: admin
 * @since: 2021/11/23 14:15
 * @history:
 */
public class MyThreadPoolExecutor {

    public static void main(String[] args) {

//        CPU 密集型应⽤，线程池⼤⼩设置为 N + 1
//        IO 密集型应⽤，线程池⼤⼩设置为 2N
//        线程池⼤⼩ = （（线程 IO time + 线程 CPU time ）/线程 CPU time ） X CPU数⽬


        // 核心线程数量
        int corePoolSize = 8;
        // 最大线程数量；
        int maximumPoolSize = 20;
        // 线程池维护线程所允许的空闲时间。当线程池中的线程数量大于corePoolSize的时候，如果这时没有新的任务提交，核心线程外的线程不会立即销毁，而是会等待，直到等待的时间超过了keepAliveTime；
        long keepAliveTime = 2000;
        TimeUnit unit = TimeUnit.DAYS;
        // 等待队列，当任务提交时，如果线程池中的线程数量大于等于corePoolSize的时候，把该任务封装成一个Worker对象放入等待队列；
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>(Integer.MAX_VALUE);
//        ArrayBlockingQueue：基于数组的先进先出队列，此队列创建时必须指定⼤⼩
//        LinkedBlockingQueue：基于链表的先进先出队列，如果创建时没有指定此队列⼤⼩，则默认为Integer.MAX_VALUE
//        synchronousQueue：这个队列⽐较特殊，它不会保存提交的任务，⽽是将直接新建⼀个线程来执⾏新来的任务

        // 它是ThreadFactory类型的变量，用来创建新线程。默认使用Executors.defaultThreadFactory() 来创建线程。
        // 使用默认的ThreadFactory来创建线程时，会使新创建的线程具有相同的NORM_PRIORITY优先级并且是非守护线程，同时也设置了线程的名称。
        ThreadFactory threadFactory = Executors.defaultThreadFactory();

        // 它是RejectedExecutionHandler类型的变量，表示线程池的饱和策略。
        // 如果阻塞队列满了并且没有空闲的线程，这时如果继续提交任务，就需要采取一种策略处理该任务。线程池提供了4种策略：
//        ThreadPoolExecutor自己已经提供了四个拒绝策略，分别是CallerRunsPolicy,AbortPolicy,DiscardPolicy,DiscardOldestPolicy
        //1、拒绝策略就是AbortPolicy。直接抛出异常。
        RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();

        //2、CallerRunsPolicy 在任务被拒绝添加后，会调用当前线程池的所在的线程去执行被拒绝的任务。
        RejectedExecutionHandler handler2 = new ThreadPoolExecutor.CallerRunsPolicy();

        //3、DiscardPolicy  会让被线程池拒绝的任务直接抛弃，不会抛异常也不会执行
        RejectedExecutionHandler handler3 = new ThreadPoolExecutor.DiscardPolicy();

        //4、DiscardOldestPolicy 先从任务队列中弹出最先加入的任务，空出一个位置，然后再次执行execute方法把任务加入队列。
        RejectedExecutionHandler handler4 = new ThreadPoolExecutor.DiscardOldestPolicy();

//        // 自定义拒绝策略
//        static class MyRejectedExecutionHandler implements RejectedExecutionHandler {
//            @Override
//            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
//                new Thread(r,"新线程"+new Random().nextInt(10)).start();
//            }
//        }

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                        corePoolSize,
                        maximumPoolSize,
                        keepAliveTime,
                        unit,
                        workQueue,
                        threadFactory,
                        handler
                );

        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    query();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public static void query() throws UnknownHostException, MalformedURLException {
        InetAddress byName = InetAddress.getByName("www.luckywhb.com");
        System.out.println("byName1.getAddress() = " + byName.getAddress());
        // 规范的名字
        System.out.println("byName1.getCanonicalHostName() = " + byName.getCanonicalHostName());
        // ip
        System.out.println("byName1.getHostAddress() = " + byName.getHostAddress());
        // 域名 电脑名
        System.out.println("byName1.getHostName() = " + byName.getHostName());
//        URL address = new URL("www.luckywhb.com");
    }


}
