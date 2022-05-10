package com.javasebase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String uername1 = req.getParameter("uername");

        req.getParameter("ee");
        System.out.println("req = " + req + ", resp = doGet" + resp);
//        super.doGet(req, resp);
        resp.setContentType("textapplication");



    }





    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("req = " + req + ", resp = doPost" + resp);
        super.doPost(req, resp);
    }
}
