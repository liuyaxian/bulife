package com.ruiya.net;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @desc:
 * @author: admin
 * @since: 2021/12/13 17:50
 * @history:
 */
public class TcpServiceDemo2 {
    public static void main(String[] args) throws IOException {
        // 创建服务
        ServerSocket serverSocket = new ServerSocket(9991);
        //监听连接 阻塞式
        Socket socket = serverSocket.accept();
        //  获取文件流
        InputStream is = socket.getInputStream();
        // 文件输出
        FileOutputStream fos = new FileOutputStream(new File("revice.png"));
        byte [] buffer = new byte[1024];
        int len;
        while ((len = is.read(buffer))!= -1){
            fos.write(buffer, 0, len);
        }

        // 通知客户端我接收完毕了
        OutputStream os = socket.getOutputStream();
        os.write("接收完毕了".getBytes());

        // 关闭流
        fos.close();
        is.close();
        socket.close();


    }

}
