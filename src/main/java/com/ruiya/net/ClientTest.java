package com.ruiya.net;

import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @desc:
 * @author: admin
 * @since: 2021/12/13 9:59
 * @history:
 */
public class ClientTest {

    public static void main(String[] args)   {
        Socket socket = null;
        OutputStream os = null;
        try {
            // 服务器地址 + 端口
            InetAddress serverIP = InetAddress.getByName("127.0.0.1");
            int port = 9999;
            // 2、 创建一个 socket 连接
            socket = new Socket(serverIP, port);
            // 3、 发送消息io流
            os = socket.getOutputStream();
            os.write("hello world".getBytes());



        }catch (Exception e){

        }finally {
            if(os != null){
                try {
                    os.close();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
            if(socket != null){
                try {
                    socket.close();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }


    }


}
