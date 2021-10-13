package com.ruiya.javabase.thread;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.net.URL;

public class TestThread extends  Thread {

    private String url;
    private String name;

    public TestThread(String url, String name){
        this.name = name;
        this.url = url;
    }

    @Override
    public void run(){
        WebDownload webDownload = new WebDownload();
        webDownload.downloader(url, name);
        System.out.println("文件名 name = " + name);

    }
    public static void main(String[] args) {
        // 同时执行
        TestThread testThread1 = new TestThread("url", "图片一");
        TestThread testThread2 =  new TestThread("url", "图片二");
        TestThread testThread3 =  new TestThread("url", "图片三");
        testThread1.start();
        testThread2.start();
        testThread3.start();

//        // 同时执行
//        SecondThread SecondThread1 = new SecondThread("https://www.baidu.com/link?url=2BVzNxJ0cRyAdhe7z8Encdq5GgtKZKHGj89EcLB9pJ8e5dpxdkB89FNxxrUGyG0oheEAZiMW0FYoRUJWu5lO6dSd669gY42ThOF6hStGtyGl4tbMWctzlU4U-aOh0F04iukEuf8SrGILbZxwzu3lM6aJQkUQY43oHT5wz71V7ZbBqYcogUYW4cCoNYDVJsu8qKgZAwDedP-J6UqQglx7xJmKoZ03rlxPNPfr48z1YN7QGOwNUA0Qd0RV8zSS6Qs2Wu5NCgzzFZtea5p14pXp0nPhkpGHnXHYgpxHtxvqt1hsaoTnObYeI6KCFKSaTfok3J2wAivwTSkoq2toMiTMdcGTjH8u9n7Nto7OXHTyiJ2J6m-N8IvPSsX-C36Q9Q33hzCDUTxcHzBvbBfiCHNQ5eOwqatj0_ezG1uXovvEjGJwf2Ny4tY9E2iLY1oe0M9bLOCijTYgP80nDdj-vf6hMd1RvDGuL-dQ-orm9pqbDfKgrG1XUUFNRbTOln1N0J3UXyHKcQ-YxKAmmPovHV4We5WGEJNZqovBWgj7pkBKcd7nfad4f5bxP3rZu6UEmT039kVIS0mmtB6g1L37guMp_K&wd=&eqid=8cf8d5bf000995eb00000006613f451b","test1");
//        SecondThread SecondThread2 = new SecondThread("https://www.baidu.com/link?url=-aOh0F04iukEuf8SrGILbZxwzu3lM6aJQkUQY43oHT5wz71V7ZbBqYcogUYW4cCoNYDVJsu8qKgZAwDed-N8IvPSsX-C36Q9Q33hzCDUTxcHzBvbBfiCHNQ5eOwqatj0_ezG1uXovvEjGJwf2Ny4tY9E2iLY1oe0M9bLOCijTYgP80nDdj-vf6hMd1RvDGuL-dQ-orm9pqbDfKgrG1XUUFNRbTOln1N0J3UXyHKcQ-YxKAmmPovHV4We5WGEJNZqovBWgj7pkBKcd7nfad4f5bxP3rZu6UEmT039kVIS0mmtB6g1L37guMp_K&wd=&eqid=8cf8d5bf000995eb00000006613f451b","test1");
//        SecondThread SecondThread3 = new SecondThread("https://www.baidu.com/link?url=2BVzNxJ0cRyAdhe7z8Encdq5GgtKZKHGj89EcLB9pJ8e5dpxdkB89FNxxrUGyG0oheEAZiMW0FYoRUJWu5lO6dSd669gY42ThOF6hStGtyGl4tbMWctzlU4U-aOh0F04iukEuf8SrGILbZxwzu3lM6aJQkUQY43oHT5wz71V7ZbBqYcogUYW4cCoNYDVJsu8qKgZAwDedP-J6UqQglx7xJmKoZ03rlxPNPfr48z1YN7QGOwNUA0Qd0RV8zSS6Qs2Wu5NCgzzFZtea5p14pXp0nPhkpGHnXHYgpxHtxvqt1hsaoTnObYeI6KCFKSaTfok3J2wAivwTSkoq2toMiTMdcGTjH8u9n7Nto7OXHTyiJ2J6m-N8IvPSsX-C36Q9Q33hzCDUTxcHzBvbBfiCHNQ5eOwqatj0_ezG1uXovvEjGJwf2Ny4tY9E2iLY1oe0M9bLOCijTYgP80nDdj-vf6hMd1RvDGuL-dQ-orm9pqbDfKgrG1XUUFNRbTOln1N0J3UXyHKcQ-YxKAmmPovHV4We5WGEJNZqovBWgj7pkBKcd7nfad4f5bxP3rZu6UEmT039kVIS0mmtB6g1L37guMp_K&wd=&eqid=8cf8d5bf000995eb00000006613f451b","test1");
//       new Thread(SecondThread1).start();
//        new Thread( SecondThread2).start();
//        new Thread( SecondThread3).start();

    }
}


class WebDownload{
    public void downloader(String url , String name)  {
        try {
            FileUtils.copyURLToFile(new URL(url), new File(name));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}