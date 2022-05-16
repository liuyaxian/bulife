package com.javasebase;

import com.yaruida.utils.MysqlJdbcUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String uername1 = req.getParameter("uername");

        req.getParameter("ee");
        System.out.println("req = " + req + ", resp = doGet" + resp);
//        super.doGet(req, resp);
        resp.setContentType("text/txt");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("req = " + req + ", resp = doPost" + resp);

        try {
            req.setAttribute("customers", MysqlJdbcUtils.test2());
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        req.getRequestDispatcher("/path/index.jsp");
        super.doPost(req, resp);
    }


    public static void main(String[] args) {

        String packageName = "com.zlfund.mobile";
            String sql = "select t1.* from interaccounts t1 ";
                sql += " left join custnotify t2 on t2.custno = t1.custno and t2.notifytype ='information' and coalesce(t2.appst,'Y') ='Y' ";
            sql += " where t1.service_name in ('Android','iOS') and t1.packagename = '" + packageName + "'";
            sql += " and t1.external_id not like 'untoken%' and t1.external_id not like '%-%'";



            

        String sql1 = "select t1.* from interaccounts t1 where t1.service_name in ('Android','iOS') and t1.packagename = '" + packageName + "'";
        sql1 += " and t1.external_id not like 'untoken%' and t1.external_id not like '%-%'";
        sql1 += " and not exists (select 1 from  custnotify t2 WHERE t2.custno = t1.custno and t2.notifytype ='information' and coalesce(t2.appst,'Y') ='F') ";

        System.out.println("sql:"+ sql1);
        }

    }
