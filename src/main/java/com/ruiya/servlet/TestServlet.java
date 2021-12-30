package com.ruiya.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @desc:
 * @author: admin
 * @since: 2021/12/29 15:49
 * @history:
 */
public class TestServlet {


    public void outHtml(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 设置编码方式
        request.setCharacterEncoding("UTF-8");
        // 获取请求参数
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        // 设置响应内容类型，  MIMETYPE =  数据类型  输出流之前设置编码方式
        response.setContentType("text/html;charset=UTF-8");
        // 获取输出流
        PrintWriter pw = response.getWriter();

        pw.write("<html> <h1> 你好呀 ！</h1> </html>");

    }




}
