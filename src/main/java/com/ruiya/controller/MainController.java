package com.ruiya.controller;


import com.ruiya.bean.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

// 给容器中自动创建两个类型的组件
@RequestMapping(value = "/", produces = "application/json; charset=utf-8")
//@Import({User.class, DBAppender.class})
@RestController
public class MainController {

    @Autowired
    private Car car;

    @RequestMapping("/car")
    public Car getCar() {
        return car;
    }

    @RequestMapping("/hello")
    public String sayHello() {
        return "hello  spring boot2!中文 ";
    }

    @RequestMapping("/goto")
    public String getCar(HttpServletRequest httpServlet) {
        Cookie[] cookies = httpServlet.getCookies();
        for (Cookie cookie : cookies) {
            System.out.println(cookie);
        }
        return "car";
    }

    @GetMapping("/map")
    public String getMap(Map<String, String> map,
                         Model model, ServletServerHttpResponse DD) {
        map.put("dd", "lusio");
        return "forward:success";
    }

    @ResponseBody
    @RequestMapping("/success")
    public Map success(HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        return parameterMap;
    }
}