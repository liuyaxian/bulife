package com.ruiya;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruiya.cache.redis.RedisTemple;
import com.ruiya.taobao.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisSpringbootApplicationTest {


    @Autowired
    private RedisTemplate redisTemplate;


    @Test
    public void test() throws JsonProcessingException {
        User user = new User();
        user.setAge(1);
        user.setGender("nan");
        user.setName("liusi");
        String jsonUser = new ObjectMapper().writeValueAsString(user);
        redisTemplate.opsForValue().set("user", user);
        System.out.printf("jint"+ redisTemplate.opsForValue().get("user"));
    }

}
