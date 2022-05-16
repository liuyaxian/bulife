package com.yaruida.utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbUtils {

    private static final String CLASSNAME = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://172.18.10.74:3306/mytest?useUnicode=true&useSSL=false&characterEncoding=utf-8&serverTimezone=Asia/Shanghai&useAffectedRows=true&allowMultiQueries=true";
    private static final String USER = "emsel";
    private static final String PASSWORD = "emselemsel";

    /**
     * 执行 DDL  DML 语句
     * 可变参数。。。。
     * @return
     */
    public static int update(String sql,  Object ...args){

        try {
            Class.forName(CLASSNAME);
            try(Connection connection = DriverManager.getConnection(URL , USER, PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(sql);){
                for (int i = 0; i < args.length; i++) {
                    preparedStatement.setObject(i+1,  args[i]);
                }

              return preparedStatement.executeUpdate();
            }
        } catch (Exception e){
            System.out.println("e.getMessage() = " + e.getMessage());
        }
        return 0;
    }


    public static <T>List<T> queryList(String sql, RowMapper<T> mapper,  Object ...args ){

        if (mapper == null){
            return  null;
        }
        try {
            Class.forName(CLASSNAME);
            try(Connection connection = DriverManager.getConnection(URL , USER, PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(sql);){
                for (int i = 0; i < args.length; i++) {
                    preparedStatement.setObject(i+1, args[i]);
                }
                ResultSet resultSet = preparedStatement.executeQuery();

                List<T>  result = new ArrayList<>();
                int row = 0;
                // 让游标指向下一行记录
                while (resultSet.next()){
                    result.add(mapper.map(resultSet, row++));
                }
                resultSet.close();
                return result;
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("e.getMessage() = " + e.getMessage());
        }
        return  null;
    }

   public   interface  RowMapper<T> {
        T map(ResultSet rs, int row) throws SQLException;
    }
}
