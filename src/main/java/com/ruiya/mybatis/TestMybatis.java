package com.ruiya.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class TestMybatis {

    public static void main(String[] args) throws IOException {
        String resource = "D:\\work\\workspace_test\\bulife\\src\\main\\java\\com\\ruiya\\mybatis\\mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();

        String statement = "";
        Object o = sqlSession.selectOne(statement);


        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        mapper.selectBlog(12);
    }
}

