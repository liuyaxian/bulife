package com.ruiya.net;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @desc:
 * @author: admin
 * @since: 2021/12/10 20:16
 * @history:
 */
public class TestIntAddress {

    public static void main(String[] args) throws UnknownHostException {
        InetAddress byName = InetAddress.getByName("127.0.0.1");
        InetAddress byName2 = InetAddress.getByName("localhost");
        InetAddress byName3 = InetAddress.getLocalHost();
        InetAddress byName1 = InetAddress.getByName("www.baidu.com");
        //
        System.out.println("byName1.getAddress() = " + byName1.getAddress());
        // 规范的名字
        System.out.println("byName1.getCanonicalHostName() = " + byName1.getCanonicalHostName());
        // ip
        System.out.println("byName1.getHostAddress() = " + byName1.getHostAddress());
        // 域名 电脑名
        System.out.println("byName1.getHostName() = " + byName1.getHostName());


        System.out.println("byName1 = " + byName1);

    }
}
