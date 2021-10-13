package com.ruiya.javabase.thread;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.net.URL;
import java.util.concurrent.*;

public class ThirdThread implements Callable {
    @Override
    public Boolean call() throws Exception {
        WebDownload webDownload = new WebDownload();
        webDownload.downloader(url, name);
        System.out.println("webDownload = " + name);
        return true;
    }

    private String url;
    private String name;

    public ThirdThread(String url, String name){
        this.name = name;
        this.url = url;
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThirdThread testThread1 = new ThirdThread("url", "图片一");
        ThirdThread testThread2 =  new ThirdThread("url", "图片二");
        ThirdThread testThread3 =  new ThirdThread("url", "图片三");
        // 1创建执行服务
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        //  提交执行
        Future<Boolean>  rq1 = executorService.submit(testThread1);
        Future<Boolean>  rq2 = executorService.submit(testThread2);
        Future<Boolean>  rq3 = executorService.submit(testThread3);

        // 3 获取结果
        boolean rs1  = rq1.get();
        boolean rs2  = rq2.get();
        boolean rs3  = rq3.get();

        // 4 关闭服务
        executorService.shutdownNow();

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
}
