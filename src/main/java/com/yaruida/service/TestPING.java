package com.yaruida.service;

import lombok.val;
import redis.clients.jedis.Jedis;

public class TestPING {

    public static void main(String[] args) {
        // new jedis 对象
        Jedis jedis = new Jedis("172.18.10.85", 6379);
        jedis.ping();

        jedis.close();
    }
}
