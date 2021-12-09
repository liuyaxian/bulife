package com.ruiya.htmlToImage;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.w3c.dom.Document;
import org.xhtmlrenderer.swing.Java2DRenderer;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

/**
 * @desc:
 * @author: admin
 * @since: 2021/12/9 18:01
 * @history:
 */
public class FreemarkerUtils {

    private static String getTemplate(String template, Map<String,Object> map) throws IOException, TemplateException, IOException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_25);
        String templatePath = FreemarkerUtils.class.getResource("/").getPath()+"/static";
        cfg.setDirectoryForTemplateLoading(new File(templatePath));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        Template temp = cfg.getTemplate(template);
        StringWriter stringWriter = new StringWriter();
        temp.process(map, stringWriter);
        stringWriter.flush();
        stringWriter.close();
        String resutl = stringWriter.getBuffer().toString();
        return resutl;
    }

    public static void turnImage(String template, Map<String,Object> map, HttpServletResponse response) throws Exception {
        String html = getTemplate(template, map);

        byte[] bytes=html.getBytes();
        ByteArrayInputStream bin=new ByteArrayInputStream(bytes);
        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        DocumentBuilder builder=factory.newDocumentBuilder();
        Document document=builder.parse(bin);
        Java2DRenderer renderer = new Java2DRenderer(document,600,800);
        BufferedImage img = renderer.getImage();
        response.setContentType("image/jpeg");
        response.setDateHeader("expries", -1);
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        ImageIO.write(img, "jpg", response.getOutputStream());
    }

}
