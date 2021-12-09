package com.ruiya;

import com.ruiya.htmlToImage.FreemarkerUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @desc:
 * @author: admin
 * @since: 2021/12/9 18:05
 * @history:
 */

public class FreemarkerUtilsTest {

    @RequestMapping(value = "/test", method = {RequestMethod.GET})
    public void test(HttpServletResponse response) {
        try {
            Map<String,Object> map=new HashMap<>();
            map.put("msg","html--生成图片测试");
            map.put("img","https://upload-images.jianshu.io/upload_images/912344-3054132dd6939004.png?imageMogr2/auto-orient/strip|imageView2/1/w/300/h/240");
            FreemarkerUtils.turnImage("index.ftl",map,response);
        } catch (Exception e) {
            System.out.println("dfsdf");
        }
    }

}
