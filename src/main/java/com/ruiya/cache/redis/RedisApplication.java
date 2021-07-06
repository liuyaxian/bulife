package com.ruiya.cache.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

public class RedisApplication {
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    //	@Autowired
//	RedisTemplate redisTemple;
}
