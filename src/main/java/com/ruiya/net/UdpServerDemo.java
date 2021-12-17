package com.ruiya.net;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @desc:
 * @author: admin
 * @since: 2021/12/13 20:36
 * @history:
 */
public class UdpServerDemo {
    public static void main(String[] args) throws Exception {
        // 1、 创建一个Socket
        DatagramSocket socket = new DatagramSocket();
        // 2、 创建一个包
        String msg = "你好";
        InetAddress localhost = InetAddress.getByName("localhost");
        int port = 9999;
        DatagramPacket packet = new DatagramPacket(msg.getBytes(), 0, msg.getBytes().length, localhost, port);

        // 3、 发送包
        socket.send(packet);
        // 4、 关闭流
        socket.close();
    }

}
