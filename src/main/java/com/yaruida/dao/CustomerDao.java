package com.yaruida.dao;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.yaruida.utils.Customer;
import com.yaruida.utils.DbSpringJdbcUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;


public class CustomerDao {


    public void save(Customer customer){
        String sql = " insert into `user` (id, name, pwd, age, insert_time) values (?, ?, ?,?,?) ";
        DbSpringJdbcUtils.getJdbcTemplate().update(sql, sql, 345, "22", "33",11, "20220516");
    }


    public  List<Customer> query() throws ClassNotFoundException, SQLException {
        String sql1 = " select id, name, pwd, age  from  `user` ";
        return DbSpringJdbcUtils.getJdbcTemplate().query(sql1, (rs, row) -> {
            Customer customer = new Customer();
            customer.setAge(rs.getInt("age"));
            customer.setId(rs.getInt("id"));
            customer.setName(rs.getString("name"));
            customer.setPwd(rs.getString("pwd"));
            return customer;
        });
    }


    public DataSource mysqlDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/springjdbc");
        dataSource.setUsername("guest_user");
        dataSource.setPassword("guest_password");
        return dataSource;
    }




    private static JdbcTemplate jdbcTemplate;

    static {
        try (InputStream resourceAsStream = CustomerDao.class.getClassLoader().getResourceAsStream("dbs.properties")){
            Properties properties = new Properties();
            properties.load(resourceAsStream);
            DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
            jdbcTemplate = new JdbcTemplate(dataSource);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Customer> list() throws Exception {
        String sql1 = " select id, name, pwd, age  from  `user` ";
        return  jdbcTemplate.query(sql1, (rs , row) -> {
            Customer customer = new Customer();
            customer.setAge(rs.getInt("age"));
            customer.setId(rs.getInt("id"));
            customer.setName(rs.getString("name"));
            customer.setPwd(rs.getString("pwd"));
            return customer;
        } );
    }
}
