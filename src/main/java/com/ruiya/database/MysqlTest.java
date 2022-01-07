package com.ruiya.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * @desc:
 * @author: admin
 * @since: 2021/12/30 16:51
 * @history:
 */
public class MysqlTest {

    public static void main(String[] args) throws Exception {
        // 加载驱动
        Class.forName("com.mysql.cj.jdbc.Driver");

        // 连接对象
        Connection connection = DriverManager.getConnection("jdbc:mysql://172.18.10.74:3306/EMdata", "emsel", "emselemsel");

        // 创建 Statemen
        Statement statement = connection.createStatement();
        // 执行sql
        statement.execute("update  SYNC_CLIENTINFO  set ESEQID = '222' ");

        statement.close();
        connection.close();

    }
}
