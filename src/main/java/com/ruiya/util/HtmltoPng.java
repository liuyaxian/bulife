package com.ruiya.util;

import org.apache.commons.io.FileUtils;
import org.springframework.util.ResourceUtils;
import org.xhtmlrenderer.swing.Java2DRenderer;
import org.xhtmlrenderer.util.FSImageWriter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @desc:
 * @author: admin
 * @since: 2021/11/29 10:36
 * @history:
 */
public class HtmltoPng {


    public static void main(String[] args) throws Exception {
        testJava2DRenderer();
    }


    public static  void testHtml2Image() throws Exception
    {
//        String html = "<!DOCTYPE html><html><head><meta charset=\"utf-8\"><title>贝叶斯统计推断</title></head><body><article class=\"markdown-body\"><h1id=\"gps\"><a name=\"user-content-gps\"href=\"#gps\"class=\"headeranchor-link\"aria-hidden=\"true\"><spanclass=\"headeranchor\"></span></a>贝叶斯统计推断</h1><p>最大后验概率MAP</p><p>最小均方误差LMS</p><blockquote><p>1.最大后验概率是在观测条件x下，寻求待估量最可能的值作为估计值，即最大后验概率时的值。<br/>2.最小均方误差是在观测条件x下，以待估量的均值作为估计值。<br/></p></blockquote></article>";

//        String pathAfterModified = HtmltoPng.class.getClassLoader().getResource("static/firstTradeRequest1.html").getPath();
//
//        String htmlStr1 = FileUtils.readFileToString(new File(pathAfterModified), StandardCharsets.UTF_8);

        // 获取htmlm模板文件
//        String pathAfterModified = HtmltoPng.class.getClassLoader().getResource("static/firstTradeRequest1.html");

        String htmlStr = FileUtils.readFileToString(new File("E:\\sourceCode\\bulife\\src\\main\\resources\\static\\firstTradeRequest.html"), StandardCharsets.UTF_8);

        JEditorPane editPane = new JEditorPane("text/html", htmlStr);
        editPane.setEditable(true);
        Dimension prefSize = editPane.getPreferredSize();
        BufferedImage img = new BufferedImage(prefSize.width,prefSize.height, BufferedImage.TYPE_INT_ARGB);

//        BufferedImage img = new BufferedImage(1110,800, BufferedImage.TYPE_INT_RGB);

        Graphics graphics = img.getGraphics();
        editPane.setSize(prefSize);
        editPane.paint(graphics);
        graphics.dispose();
        ImageIO.write(img, "png",  new File("testImage11.png"));
    }

    public static void testJava2DRenderer() throws IOException {

        String htmlStr = ResourceUtils.getURL("static/firstTradeRequest1.html").getPath();
        //string转为inputstream流
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(htmlStr.getBytes());
//        File targetFile = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX+"firstTradeRequest.html");
        File targetFile = ResourceUtils.getFile(htmlStr);
        //inputstream流转为file
        FileUtils.copyInputStreamToFile(byteArrayInputStream, targetFile);

//        File targetFile = new File("static/firstTradeRequest.html");
        //通过openhtmltopdf工具生成图片
        final Java2DRenderer renderer = new Java2DRenderer(targetFile, 740, 1000);
        final BufferedImage img = renderer.getImage();
        final FSImageWriter imageWriter = new FSImageWriter();
        imageWriter.setWriteCompressionQuality(0.9f);
        imageWriter.write(img, "output.png");//输出路径
        System.out.println("Done with rendering");
    }
}


