package com.yaruida.service;

import com.google.gson.JsonObject;
import lombok.val;
import redis.clients.jedis.Jedis;

public class TestTX {

    public static void main(String[] args) {
        // new jedis 对象
        val jedis = new Jedis("172.18.10.85", 6379);
        jedis.ping();
        // 开启事务
        val multi = jedis.multi();

        multi.exec();

        try {

        }catch (Exception e){

        }finally {

        }

        jedis.close();
    }
}
