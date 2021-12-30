package com.ruiya.util.http;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @desc:
 * @author: admin
 * @since: 2021/12/28 10:42
 * @history:
 */
public class HttpHelper {

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

    public void get(){
//        HttpConnectionManager httpConnectionManager = new MultiThreadedHttpConnectionManager();
//        httpConnectionManager.getParams().setConnectionTimeout(3 * 1000);
//        httpConnectionManager.getParams().setSoTimeout(1 * 1 * 1000);
//        // 每个host支持多少个并发
//        httpConnectionManager.getParams().setDefaultMaxConnectionsPerHost(512);
//        // 总共支持多少个链接， host个数*DefaultMaxConnectionsPerHost
//        httpConnectionManager.getParams().setMaxTotalConnections(5 * 256);

//        HttpClient client = new HttpClient();
//
//        // 构造请求url
//        PostMethod method = new PostMethod("www.baidu.com");
//        method.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
//        String httpResp = "";
//        client.executeMethod(method);
//        if (method.getStatusCode() == 200){
//            httpResp = method.getResponseBodyAsString();
//        }
    }
}
