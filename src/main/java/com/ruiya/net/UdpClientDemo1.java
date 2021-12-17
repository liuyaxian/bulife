package com.ruiya.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @desc:
 * @author: admin
 * @since: 2021/12/13 20:35
 * @history:
 */
public class UdpClientDemo1 {

    public static void main(String[] args) throws IOException {
        // 开放端口
        DatagramSocket socket = new DatagramSocket(9090);
        // 接收数据包
        byte [] buffer = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buffer, 0, buffer.length);
        socket.receive(packet);

        System.out.println("packet.getAddress().getHostAddress() = " + packet.getAddress().getHostAddress());

        new String(packet.getData().toString());
        socket.close();
    }
}


