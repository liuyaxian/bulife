package com.ruiya.jdk.jdk9;

import java.net.http.HttpClient;

public class HttpTest1 {

    public static void main(String[] args) {
        HttpClient client = HttpClient
                .newBuilder()
                .version(HttpClient.Version.HTTP_2)  //支持HTTP2
                .build();


    }
}