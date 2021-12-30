package com.ruiya.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @desc:
 * @author: admin
 * @since: 2021/12/30 9:15
 * @history:
 */
@WebServlet("/test")
public class ServletTest extends HttpServlet {

    private  int age = 10;
    public ServletTest(){
        System.out.printf(this + "servlet Test" + "构造方法！");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        System.out.printf("ewrw");
    }
}
