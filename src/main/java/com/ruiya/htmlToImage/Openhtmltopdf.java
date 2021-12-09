package com.ruiya.htmlToImage;

/**
 * @desc:
 * @author: admin
 * @since: 2021/12/9 17:53
 * @history:
 */

import org.apache.commons.io.FileUtils;
import org.springframework.util.ResourceUtils;
import org.xhtmlrenderer.swing.Java2DRenderer;
import org.xhtmlrenderer.util.FSImageWriter;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/***
 * <dependency>
 *     <groupId>com.openhtmltopdf</groupId>
 *     <artifactId>openhtmltopdf-core</artifactId>
 *     <version>0.0.1-RC9</version>
 * </dependency>
 */
public class Openhtmltopdf {
    public static void main(String[] args) throws IOException {
        String htmlStr = FileUtils.readFileToString(new File("E:\\sourceCode\\bulife\\src\\main\\resources\\static\\firstTradeRequest.html"), StandardCharsets.UTF_8);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(htmlStr.getBytes());
        File targetFile = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX+"firstTradeRequest.html");
        //inputstream流转为file
        FileUtils.copyInputStreamToFile(byteArrayInputStream, targetFile);
        //通过openhtmltopdf工具生成图片
        final Java2DRenderer renderer = new Java2DRenderer(targetFile, 740, 1000);
        final BufferedImage img = renderer.getImage();
        final FSImageWriter imageWriter = new FSImageWriter();
        imageWriter.setWriteCompressionQuality(0.9f);
        imageWriter.write(img, "output.png");//输出路径
        System.out.println("Done with rendering");
    }
}
