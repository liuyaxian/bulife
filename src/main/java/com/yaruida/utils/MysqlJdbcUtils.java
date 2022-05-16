package com.yaruida.utils;

import lombok.val;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class MysqlJdbcUtils {



    private static final String CLASSNAME = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://172.18.10.74:3306/mytest?useUnicode=true&useSSL=false&characterEncoding=utf-8&serverTimezone=Asia/Shanghai&useAffectedRows=true&allowMultiQueries=true";
    private static final String USER = "emsel";
    private static final String PASSWORD = "emselemsel";

    public static void connectionMysql(String name, String pwd) throws ClassNotFoundException, SQLException, IOException {

        //1、 加载文件
        InputStream is = MysqlJdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties properties = new Properties();
        properties.load(is);


        // 2、 读取配置信息
        String driverClass = properties.getProperty("jdbc.dirverClass");
        String url = properties.getProperty("jdbc.url");
        String user = properties.getProperty("jdbc.user");
        String password = properties.getProperty("jdbc.password");

        // 3、 加载驱动
        Class.forName(driverClass);

        // 4、获取连接
        String sql = " select * from  `user` where name = ? and pwd = ? ";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            // 设置参数
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, pwd);
            val resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println("登录成功");
            } else {
                System.out.println("登录失败");
            }

        }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
        // sql    inject
//        test3("123", " '' OR '1' = '1' ");
//        login2("123", "345");
        test211();
//        connectionMysql("123", "345");
    }



    public static void test() throws SQLException {
        String className = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://172.18.10.74:3306/mytest?useUnicode=true&useSSL=false&characterEncoding=utf-8&serverTimezone=Asia/Shanghai&useAffectedRows=true&allowMultiQueries=true";
        String user = "emsel";
        String password = "emselemsel";
        // 1、注册driver 到 drivermanager  驱动类, 从jdbc 4.0 开始（java6开始） 驱动包不需要写，只需要将jar报放到类路径即可， DriverManager 自动识别
//        Class.forName("com.mysql.jdbc.Driver");
//        Class.forName(className);

        // 2、 利用dirvermanager 连接数据库
        // Caused by: javax.net.ssl.SSLHandshakeException: No appropriate protocol(protocol is disabled
        Connection connection = DriverManager.getConnection(url , user, password);

        // 3、 利用connection c创建 statement 语句
        Statement statement = connection.createStatement();
        // 4、利用statement 执行sql 语句
        String sql = " insert into `user` (id, name, pwd, age, insert_time) values (112, 'ee', '1111', 14, now()) ";
        statement.execute(sql);
        statement.executeUpdate(sql);
        statement.executeLargeUpdate(sql);
        // 5、关闭 资源 关闭数据库连接
        statement.close();
        connection.close();
    }

    public static void test1() throws ClassNotFoundException, SQLException {
        String className = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://172.18.10.74:3306/mytest?useUnicode=true&useSSL=false&characterEncoding=utf-8&serverTimezone=Asia/Shanghai&useAffectedRows=true&allowMultiQueries=true";
        String user = "emsel";
        String password = "emselemsel";
        try {
            // 1、注册driver 到 drivermanager  驱动类
//        Class.forName("com.mysql.jdbc.Driver");
            Class.forName(className);

            // 2、 利用dirvermanager 连接数据库
            // Caused by: javax.net.ssl.SSLHandshakeException: No appropriate protocol(protocol is disabled
            // 3、 利用connection c创建 statement 语句
            // 这时如果try块和finally块中的方法都抛出异常那么try块中的异常会被抑制（suppress），只会抛出finally中的异常，而把try块的异常完全忽略。
            try(Connection connection = DriverManager.getConnection(url , user, password);
                     Statement statement = connection.createStatement();){
                // 4、利用statement 执行sql 语句
                String sql = " insert into `user` (id, name, pwd, age, insert_time) values (33, 'ee', '1111', 14, now()) ";
                statement.execute(sql);
            }

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("e.getMessage() = " + e.getMessage());
        }

    }
    public static void test12() throws ClassNotFoundException, SQLException {
        String sql = " insert into `user` (id, name, pwd, age, insert_time) values (?, ?, ?,?,?) ";
            DbUtils.update(sql, 345, "22", "33",11, "20220516");
    }

    public static  List<Customer>  test2() throws ClassNotFoundException, SQLException {
        List<Customer> customerList = new ArrayList<>();

        try {
            Class.forName(CLASSNAME);
            try(Connection connection = DriverManager.getConnection(URL , USER, PASSWORD);
                Statement statement = connection.createStatement();){
                String sql1 = " select id, name, pwd, age  from  `user` ";
                // 游标
                ResultSet resultSet = statement.executeQuery(sql1);
                // 让游标指向下一行记录
                while (resultSet.next()){
                    Customer customer = new Customer();
                    customer.setAge(resultSet.getInt("age"));
                    customer.setId(resultSet.getInt("id"));
                    customer.setName(resultSet.getString("name"));
                    customer.setPwd(resultSet.getString("pwd"));
                    customerList.add(customer);
                }
                System.out.println(customerList.toString());
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("e.getMessage() = " + e.getMessage());
        }
        return customerList;
    }

    public static  List<Customer>  test21() throws ClassNotFoundException, SQLException {

        String sql1 = " select id, name, pwd, age  from  `user` ";

//       return DbUtils.queryList(sql1, (rs, row) ->{
//            Customer customer = new Customer();
//            customer.setAge(rs.getInt("age"));
//            customer.setId(rs.getInt("id"));
//            customer.setName(rs.getString("name"));
//            customer.setPwd(rs.getString("pwd"));
//            return customer;
//        });

        return  DbUtils.queryList(sql1, new DbUtils.RowMapper<Customer>() {
            @Override
            public Customer map(ResultSet rs, int row) throws SQLException {
                Customer customer = new Customer();
            customer.setAge(rs.getInt("age"));
            customer.setId(rs.getInt("id"));
            customer.setName(rs.getString("name"));
            customer.setPwd(rs.getString("pwd"));

                System.out.println("row"+row + customer.toString());
            return customer;
            }
        });
    }

    public static  List<Customer>  test22() throws ClassNotFoundException, SQLException {

        String sql1 = " select id, name, pwd, age  from  `user` where name= ? ";

       return DbUtils.queryList(sql1, (rs, row) ->{
            Customer customer = new Customer();
            customer.setAge(rs.getInt("age"));
            customer.setId(rs.getInt("id"));
            customer.setName(rs.getString("name"));
            customer.setPwd(rs.getString("pwd"));
           System.out.println("row"+row + customer.toString());
           return customer;
        }, "ee");
    }

    public static  List<Teacher>  test211() throws ClassNotFoundException, SQLException {
        String sql1 = " select id, name from  `teacher` where name= ? ";
        return DbUtils.queryList(sql1, (rs, row) ->{
            Teacher teacher = new Teacher();
            teacher.setId(rs.getInt("id"));
            teacher.setName(rs.getString("name"));
            System.out.println("row"+row + teacher.toString());
            return teacher;
        }, "444");
    }

    public static void test3(String username, String password) throws ClassNotFoundException, SQLException {

        try {
            Class.forName(CLASSNAME);
            try(Connection connection = DriverManager.getConnection(URL , USER, PASSWORD);
                Statement statement = connection.createStatement();){
                String sql1 = " select * from  `user` where name =" + username + " and pwd = " + password;
                val resultSet = statement.executeQuery(sql1);
                if(resultSet.next()){
                    System.out.println("登录成功");
                } else {
                    System.out.println("登录失败");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("e.getMessage() = " + e.getMessage());
        }

    }


    public static void login2(String username, String password)  {
        try {
            Class.forName(CLASSNAME);
            String sql = " select id, name, pwd, age  from  `user` where name = ? and pwd = ? ";
            try(Connection connection = DriverManager.getConnection(URL , USER, PASSWORD);
                            PreparedStatement preparedStatement = connection.prepareStatement(sql);){
                // 设置参数
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                val resultSet = preparedStatement.executeQuery();
                if(resultSet.next()){
                    System.out.println("登录成功");
                } else {
                    System.out.println("登录失败");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("e.getMessage() = " + e.getMessage());
        }
    }
}



