package com.yaruida.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import lombok.val;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.Properties;

public class DbSpringJdbcUtils {
    private static JdbcTemplate jdbcTemplate;
    static {
        // 关闭流
        try(val resourceAsStream = DbUtils.class.getClassLoader().getResourceAsStream("druid.properities");) {
            Properties properties = new Properties();
            properties.load(resourceAsStream);
            DataSource dataSource =  DruidDataSourceFactory.createDataSource(properties);
            jdbcTemplate = new JdbcTemplate(dataSource);
        } catch (Exception e) {
            e.getMessage();
        }
    }
    public static JdbcTemplate getJdbcTemplate(){
        return  jdbcTemplate;
    }
}
