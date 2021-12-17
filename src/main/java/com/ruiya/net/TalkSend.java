package com.ruiya.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

/**
 * @desc:
 * @author: admin
 * @since: 2021/12/13 20:51
 * @history:
 */
public class TalkSend implements  Runnable{

    DatagramSocket socket;
    DatagramPacket packet;
    BufferedReader reader;
    private int fromPort ;
    private int toPort ;
    private String toIp ;

    public TalkSend() {
    }

    public TalkSend(String toIp, int toPort, int fromPort) {
        this.toIp = toIp;
        this.toPort = toPort;
        this.fromPort = fromPort;
        try {
            this.socket = new DatagramSocket(fromPort);
        }catch ( Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while(true){
            try {
                reader = new BufferedReader(new InputStreamReader(System.in));
                String  data = reader.readLine();
                byte[] datas = data.getBytes();
                packet = new DatagramPacket(datas, 0, datas.length, new InetSocketAddress(this.toIp, this.toPort));
                socket.send(packet);
                System.out.println( fromPort+ ": " + new String(datas));
                if (datas.equals("bye")){
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        socket.close();
    }
}
