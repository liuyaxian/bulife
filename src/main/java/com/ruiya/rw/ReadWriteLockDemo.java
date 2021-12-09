package com.ruiya.rw;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @desc:
 * @author: admin
 * @since: 2021/11/29 19:47
 * @history:
 */
public class ReadWriteLockDemo {

    public static void main(String[] args) {
        MyCacheLock myCache = new MyCacheLock();

        for (int i = 1; i < 10; i++) {
            final int temp = i;
            new Thread(() ->{
            myCache.put(""+temp, ""+temp);
            }, String.valueOf(i)).start();
        }

        for (int i = 1; i < 10; i++) {
            final int temp = i;
            new Thread(() ->{
                myCache.get(""+temp);
            }, String.valueOf(i)).start();
        }

    }
}


class  MyCache{
    private  volatile Map<String , Object> map = new HashMap<>();


    public void put(String key, Object value){
        System.out.println("Thread.currentThread().getName()+ \"写入\" + key = " + Thread.currentThread().getName() + "写入" + key);
        map.put(key,value);
        System.out.println("Thread.currentThread().getName()+ \"写入ok\" = " + Thread.currentThread().getName() + "写入ok");



    }

    ///取读
    public void get(String  key){
        Object o = map.get(key);
    }
}


// 加锁
class  MyCacheLock{
    private  volatile Map<String , Object> map = new HashMap<>();
        // 读写锁
    //
       private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private Lock lock = new ReentrantLock();


    // 写入只希望同时只有一个线程写
    public void put(String key, Object value){
        readWriteLock.writeLock().lock();
        try {
            System.out.println("Thread.currentThread().getName()+ \"写入\" + key = " + Thread.currentThread().getName() + "写入" + key);
            map.put(key,value);
            System.out.println("Thread.currentThread().getName()+ \"写入ok\" = " + Thread.currentThread().getName() + "写入ok");

        } catch (Exception E){

        }finally {
            readWriteLock.writeLock().unlock();
        }


    }

    ///取读  所有人都可以读
    public void get(String  key){
        readWriteLock.readLock().lock();
        try {
            System.out.println("Thread.currentThread().getName()+ \"读\" + key = " + Thread.currentThread().getName() + "写入" + key);

            Object o = map.get(key);
            System.out.println("Thread.currentThread().getName()+ \"读  ok\" = " + Thread.currentThread().getName() + "写入ok");

        }catch (Exception E){

        }finally {
            readWriteLock.readLock().unlock();
        }
    }

}
