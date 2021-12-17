package com.ruiya.net;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @desc:
 * @author: admin
 * @since: 2021/12/13 11:27
 * @history:
 */
public class TcpClientDemo2 {

    public static void main(String[] args) throws Exception {
        // 创建一个socket 连接
        Socket socket = new Socket(InetAddress.getByName("127.0.0.1"), 9991);
        // 创建一个输入流
        OutputStream os = socket.getOutputStream();
        // 读取文件
        FileInputStream fis = new FileInputStream(new File("src/main/java/com/ruiya/net/ServiceTest.java"));
        //写文件
        byte [] buffer = new byte[1024];
        int len;
        while ((len = fis.read(buffer))!= -1){
            os.write(buffer, 0,  len);
        }
        // 通知服务器我已经结束了  ,
        socket.shutdownOutput();

        // 确认服务器接收完毕， 才能够断开连接
        InputStream inputStream = socket.getInputStream();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte [] buffer2 = new byte[1024];
        int len2;
        while((len2 = inputStream.read(buffer2))!=-1){
            baos.write(buffer2, 0, len2);
        }

        System.out.println("baos = " + baos);
        // 关闭资源
        baos.close();
        inputStream.close();
        os.close();
        fis.close();
        socket.close();



    }
}
