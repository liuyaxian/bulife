package com.yaruida.utils;

import lombok.val;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class DbUtils {

    private static DataSource dataSource;

    static {
        // 关闭流
        try(val resourceAsStream = DbUtils.class.getClassLoader().getResourceAsStream("/druid.properities");) {
            Properties properties = new Properties();
            properties.load(resourceAsStream);
//           dataSource =  DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    /**
     * 执行 DDL  DML 语句
     * 可变参数。。。。
     * @return
     */
    public static int update(String sql,  Object ...args){

        try {
            Connection connection = dataSource.getConnection();
            try(PreparedStatement preparedStatement = connection.prepareStatement(sql);){
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
            Connection connection = dataSource.getConnection();
            try(PreparedStatement preparedStatement = connection.prepareStatement(sql);){
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

                try{
                    if (!resultSet.wasNull()){
                        resultSet.close();
                    }
                }catch (Exception e){
                    e.getMessage();
                }
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
