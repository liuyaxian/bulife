package com.ruiya.util.filedownloadzip;

/**
 * @desc:
 * @author: admin
 * @since: 2021/12/7 15:55
 * @history:
 */

import gui.ava.html.parser.HtmlParser;
import gui.ava.html.parser.HtmlParserImpl;
import gui.ava.html.renderer.ImageRenderer;
import gui.ava.html.renderer.ImageRendererImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URL;

/***
 * <dependency>
 * 	<groupId>com.github.xuwei-k</groupId>
 * 	<artifactId>html2image</artifactId>
 * 	<version>0.1.0</version>
 * </dependency>
 */
public class HtmlToImage {


    public static void main(String[] args) {
//        String HtmlTemplateStr = readFile("E:\\sourceCode\\bulife\\src\\main\\resources\\static\\firstTradeRequest.html");

        // 获取htmlm模板文件
        URL pathAfterModified = HtmlToImage.class.getClassLoader().getResource("static/firstTradeRequest1.html");
        String HtmlTemplateStr = readFile(pathAfterModified.getPath());

        HtmlParser htmlParser = new HtmlParserImpl();
        htmlParser.loadHtml(HtmlTemplateStr);
        // html 是我的html代码
        ImageRenderer imageRenderer = new ImageRendererImpl(htmlParser);
        imageRenderer.saveImage("E:\\lcxq1.png");
    }

    //filePathAndName : 你要转换的html的 绝对路径

    public static String readFile(String filePathAndName) {
        String fileContent = "";
        try {
            File f = new File(filePathAndName);
            if(f.isFile()&&f.exists()){
                InputStreamReader read = new InputStreamReader(new FileInputStream(f),"UTF-8");
                BufferedReader reader=new BufferedReader(read);
                String line;
                while ((line = reader.readLine()) != null) {
                    //将读取到的字符拼接
                    fileContent += line;
                }
                read.close();
            }
        } catch (Exception e) {
            System.out.println("读取文件内容操作出错");
            e.printStackTrace();
        }
        System.out.println("fileContent:"+fileContent);
        return fileContent;
    }

}
