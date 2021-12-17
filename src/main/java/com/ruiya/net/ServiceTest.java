package com.ruiya.net;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @desc:
 * @author: admin
 * @since: 2021/12/13 10:44
 * @history:
 */
public class ServiceTest {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        InputStream inputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        Socket socket = null;
        // 有一个地址
        try {
            serverSocket  =  new ServerSocket(9999);
            while(true) {
                // 等待连接
                socket = serverSocket.accept();

                inputStream = socket.getInputStream();

                byteArrayOutputStream = new ByteArrayOutputStream();

                byte [] buffer = new byte[1024];
                int len;

                while((len = inputStream.read(buffer))!= -1){
                    byteArrayOutputStream.write(buffer, 0 , len);
                }

                System.out.println(byteArrayOutputStream.toString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
           if(byteArrayOutputStream != null){
               try {
                   byteArrayOutputStream.close();

               } catch (Exception e ){
                   e.printStackTrace();;
               }
           }
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (Exception e ){
                    e.printStackTrace();;
                }
            }
            if (socket != null){
                try {
                    socket.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            if(serverSocket != null){
                try {
                    serverSocket.close();
                } catch (Exception e ){
                    e.printStackTrace();;
                }
            }
        }

    }

}
