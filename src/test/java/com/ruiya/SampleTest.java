package com.ruiya;

import com.ruiya.bean.Fundinfo;
import com.ruiya.mybatisplus.postgresql.mapper.ProductMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class SampleTest {

    @Autowired
    private ProductMapper productMapper;

    @Test
    public void testSelect(){
        System.out.println("ddddddd start --- ");
//        List<Fundinfo> productInfoList = productMapper.selectList(null);
//        productInfoList.forEach(System.out::println);

    }

}
