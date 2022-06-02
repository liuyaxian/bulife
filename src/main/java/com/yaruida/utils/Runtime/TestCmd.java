package com.yaruida.utils.Runtime;

import java.io.IOException;

public class TestCmd {


    public static void main(String[] args) throws InterruptedException, IOException {

//        runExtApp("C:\\Users\\liu_y\\Desktop\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe");
        Runtime runtime = Runtime.getRuntime();
        runtime.exec("cmd /c write");

//        System.out.println("-------str变量未使用前-------------");
//        System.out.println("JVM试图使用的最大内存量："+runtime.maxMemory()+"字节");
//        System.out.println("JVM空闲内存量："+runtime.freeMemory()+"字节");
//        System.out.println("JVM内存总量："+runtime.totalMemory()+"字节");
//
//        String str = "00";
//        for (int i=0;i<2000;i++){
//            str += "xx"+i;
//        }
//        System.out.println("-------str变量使用后-------------");
//        System.out.println("JVM试图使用的最大内存量："+runtime.maxMemory()+"字节");
//        System.out.println("JVM空闲内存量："+runtime.freeMemory()+"字节");
//        System.out.println("JVM内存总量："+runtime.totalMemory()+"字节");
//        runtime.gc();
//        System.out.println("-------垃圾回收后-------------");
//        System.out.println("JVM试图使用的最大内存量："+runtime.maxMemory()+"字节");
//        System.out.println("JVM空闲内存量："+runtime.freeMemory()+"字节");
//        System.out.println("JVM内存总量："+runtime.totalMemory()+"字节");
//        System.out.println("程序开始,延时10s后程序退出...");
////        Thread.sleep(10000);
////        runtime.exit(0);
//        System.out.println("JVM已经关闭，此句不会被输出...");
//
////        runtime.exec("cmd /c echo 123");
////        runtime.exec("cmd /c dir E:\\gxg");
////        runtime.exec("cmd /c E:\\jarDir\\test.jar");
////        runtime.exec("cmd /c E:\\Study_Note\\html\\Html_in-depth.docx");
////        runtime.exec("cmd /c D:\\PotPlayer\\PotPlayerMini.exe");
////        runtime.exec("cmd /c calc");
//
//
//        try {
//            Process process = runtime.exec("cmd /c dir %JAVA_HOME%",null);//可以直接获取windows系统的环境变量值
//            InputStream inputStream = process.getInputStream();
//            InputStreamReader streamReader = new InputStreamReader(inputStream,"gbk");
//            BufferedReader bufferedReader = new BufferedReader(streamReader);
//            String readLine ;
//            while ((readLine = bufferedReader.readLine())!=null){
//                System.out.println("1"+readLine);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//
//        }
    }



    /**
     * 打开指定的程序
     * 如windows自带的：记事本程序notepad、计算器程序：notepad.exe、服务程序：services.msc 等等
     * 以及安装的第三方程序：D:\Foxmail_7.2\Foxmail.exe、D:\gxg_client\Client.exe 等等
     * 或者直接传参，如打开potPlayer播放视频："D:\PotPlayer\PotPlayerMini.exe E:\xfmovie\WoxND7209-mobile.mp4"
     * @param appNameORPath：取值如上所示
     */
    public static final void runExtApp(String appNameORPath) {
        try {
            Runtime runtime = Runtime.getRuntime();
            runtime.exec(appNameORPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
