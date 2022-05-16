package com.yaruida.servlet;

import com.yaruida.constant.Constant;
import com.yaruida.utils.Customer;

import java.sql.*;


public class UserServlet {

    public boolean save (Customer customer1){

        try {
            Class.forName(Constant.CLASSNAME);
            String sql1 = " insert into user (id, name, pwd , age ) value (?,?,?,?)";
            try(Connection connection = DriverManager.getConnection(Constant.URL , Constant.USER, Constant.PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(sql1);){
                if (preparedStatement.execute()){
                    System.out.println("成功");
                    return true;
                } else {
                    System.out.println("失败");
                    return false;
                }

            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("e.getMessage() = " + e.getMessage());
        }
        return false;
    }
}
