package com.yaruida.utils;

import com.yaruida.constant.Constant;
import org.apache.http.client.HttpClient;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @desc:
 * @author: admin
 * @since: 2022/2/11 17:15
 * @history:
 */
public class WebsiteHTtrack {



    private static void getWebsiteInfo() throws UnknownHostException {
        InetAddress byName1 = InetAddress.getByName(Constant.VN_URL);
        System.out.println("byName1.getAddress() = " + byName1.getAddress());
        // 规范的名字
        System.out.println("byName1.getCanonicalHostName() = " + byName1.getCanonicalHostName());
        // ip
        System.out.println("byName1.getHostAddress() = " + byName1.getHostAddress());
        // 域名 电脑名
        System.out.println("byName1.getHostName() = " + byName1.getHostName());

        System.out.println("byName1 = " + byName1);
    }



     public void getHttpClient(){
         HttpClient client = new Client();
         GetMethod get = new GetMethod(url);

     }


    public static void main(String[] args) throws UnknownHostException {
        getWebsiteInfo();
    }

}
