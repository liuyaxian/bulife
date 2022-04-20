package com.ruiya.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConfiguration;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfig {


    public RedisTemplate<String, Object> redisTemplate(RedisConfiguration redisConfiguration){
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(template.getRequiredConnectionFactory());
    return template;
    }

}
