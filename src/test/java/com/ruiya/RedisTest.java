package com.ruiya;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruiya.taobao.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.Set;
import java.util.concurrent.TimeUnit;

@Slf4j
@SpringBootTest
public class RedisTest {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

	@Autowired
    RedisTemplate redisTemple;

    @Test
    void testRedisRemoveAndMongoDBDelElse() throws JsonProcessingException {
        String  key = "Message [bizcode=3117, mctcode=1000, bodyJson={\"code\":\"XFJJ_SY\"}, idfa=null, imei=null, appVersion=4.3.7, retDataType=null]";
        String  key1 ="Message [bizcode=3127, mctcode=1000, bodyJson={}, idfa=null, imei=null, appVersion=4.3.7, retDataType=null]";
        Set<String> keySet = stringRedisTemplate.keys("Message [bizcode=3127,"+"*");
        for (String s : keySet) {
            stringRedisTemplate.delete(s);
        }


        String  writeValueAsString = new ObjectMapper().writeValueAsString(new User());

    }


    @Test
    void testStringRedisTemplateElse() {
        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
        valueOperations.set("liu","test");
        valueOperations.increment("content");
        stringRedisTemplate.delete("content");
        System.out.println(valueOperations.get("liu"));
        // 向redis里存入数据和设置缓存时间
        stringRedisTemplate.opsForValue().set("test", "100",60*10, TimeUnit.SECONDS);
        //val做-1操作
        stringRedisTemplate.boundValueOps("test").increment(-1);
        //根据key获取缓存中的val
        stringRedisTemplate.opsForValue().get("test");
        //val +1
        stringRedisTemplate.boundValueOps("test").increment(1);
        //根据key获取过期时间
        stringRedisTemplate.getExpire("test");
        //根据key获取过期时间并换算成指定单位
        stringRedisTemplate.getExpire("test", TimeUnit.SECONDS);
        //根据key删除缓存
        stringRedisTemplate.delete("test");
        //检查key是否存在，返回boolean值
        stringRedisTemplate.hasKey("546545");
        //向指定key中存放set集合
        stringRedisTemplate.opsForSet().add("red_123", "1","2","3");
        //设置过期时间
        stringRedisTemplate.expire("red_123",1000 , TimeUnit.MILLISECONDS);
        //根据key查看集合中是否存在指定数据
        stringRedisTemplate.opsForSet().isMember("red_123", "1");
        //根据key获取set集合
        stringRedisTemplate.opsForSet().members("red_123");
        Assertions.assertNull(valueOperations.get("rrr"));
    }

}
