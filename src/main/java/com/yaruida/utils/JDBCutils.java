package com.yaruida.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCutils {


    public static void main(String[] args) {
        get();
    }

    public static void get(){
        String className = "org.postgresql.Driver";
        Connection con = null;
        Statement stmt = null;
        try {
            Class.forName(className);
            con = DriverManager.getConnection("jdbc:postgresql://172.18.10.73:54320/trade",
                    "zlfund", "zlfundzlfund");
            stmt = con.createStatement();
            String sql = "   select sessionkey from custinfo a " +
                    "    inner join custinfoex b using(custno) " +
                    "     limit 1 ";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {

            }
            stmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
}
