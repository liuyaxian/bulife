package com.yaruida.servlet;

import lombok.val;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;


@WebServlet("/customer/list")
public class CustomerServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            String uri = req.getRequestURI();
            val split = uri.split("/");
            val s = split[split.length - 1];
            Method method = getClass().
                    getMethod(split[split.length - 1],
                            HttpServletRequest.class,
                            HttpServletResponse.class);
            method.invoke(this, req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public  void save(HttpServletRequest req, HttpServletResponse resp){
        System.out.println("save");
    }

    public void list(HttpServletRequest req, HttpServletResponse resp){
        System.out.println("list");
    }


    public void remove(HttpServletRequest req, HttpServletResponse resp){
        System.out.println("remove");

    }


    public static void main(String[] args) {
        Method [] methods = CustomerServlet.class.getMethods();

        for (Method method : methods) {
            System.out.println(method.getName());
        }

        methods = CustomerServlet.class.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
        }


        val fields = CustomerServlet.class.getFields();
        for (Field field : fields) {
            System.out.println(field.getName());
        }
        val declaredFields = CustomerServlet.class.getDeclaredFields();
        for (Field field : declaredFields) {
            System.out.println(field.getName());
        }
    }


}
