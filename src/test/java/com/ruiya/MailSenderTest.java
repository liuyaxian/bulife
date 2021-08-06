package com.ruiya;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.UnsupportedEncodingException;

@SpringBootTest
public class MailSenderTest {

    @Autowired
    private JavaMailSender mailSender;

    @Test
    public void sendMailSender(){
        // 消息构造器
        SimpleMailMessage message = new SimpleMailMessage();
        //发件人
        message.setFrom("service@zlfund.cn");
        // 收件人
        message.setTo("liu_yaxian@126.com");
        //主题
        message.setSubject("测试！！！！！");
        //      正文
        message.setText("你好呀");
        // 发送
        mailSender.send(message);
        System.out.println("发送成功！ ");
    }


    /**
     * 含有附件的邮件发送
     * @throws MessagingException
     * @throws UnsupportedEncodingException
     */
    @Test
    public void sendMailAttachmentSenderTest() throws MessagingException, UnsupportedEncodingException {
        // 消息构造器
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        //发件人 设置昵称
//        helper.setFrom("service@zlfund.cn");
        helper.setFrom(new InternetAddress("service@zlfund.cn", "众禄基金", "UTF-8"));
        // 收件人 设置昵称
        helper.setTo(new InternetAddress("liu_yaxian@126.com", "刘亚仙", "UTF-8"));
        helper.setReplyTo(new InternetAddress("liuyx@zlfund.cn", "刘亚仙", "UTF-8"));

        //主题
        helper.setSubject("众禄基金：请查收xxx的资产证明");
        //      正文
//        helper.setText("你好呀, 含有附件");

        // 使用spring 的FileSystemResource 来加载 文件
        FileSystemResource fileSystemResource = new FileSystemResource("E:\\刘亚仙\\ITextTest5.pdf");
        System.out.println(fileSystemResource.exists());
        // 文件名   文件
        helper.addAttachment("孙小美的资产证明.pdf", fileSystemResource);
//        // 带有多个附件
//        FileSystemResource file1 = new FileSystemResource(new File("C:/Users/Administrator/Desktop/eee.png"));
//        helper.addAttachment("ees.png",file1);
//        FileSystemResource file2 = new FileSystemResource(new File("C:/Users/Administrator/Desktop/test.jpg"));
//        helper.addAttachment("test.jpg",file2);

        //嵌入图片
        //邮件内容，第二个参数指定发送的是HTML格式
        //说明：嵌入图片<img src='cid:head'/>，其中cid:是固定的写法，而aaa是一个contentId。
        String sb = "<html><body> <a href='https://www.jjmmw.com' target='_blank'> <img src='cid:logo' /> </a>" +
                "</br></br><hr style ='color:#999' /> <p><strong>尊敬的用户：您好！</strong></p> "  +
                "<p>请下载附件，查收孙小美的资产证明。</p>" +
                "<p>本邮件为系统自动发送，请勿直接回复。</p>" +
                "</br></br> <hr style ='color:#999' />" +

                "<p style ='font-size: 12px; font-family: 'open sans''>*温馨提示：</p>" +
                "<p style ='font-size: 12px; font-family: 'open sans'> 1. 资产证明已加密，密码为您的证件号码后6位，不足6位则使用全部（若含有字母，需区分大小写）；</p>" +
                "<p style ='font-size: 12px; font-family: 'open sans'> 2. 如需纸质版，请用A4纸彩色/黑白打印资产证明；<p>" +
                "<p style ='font-size: 12px; font-family: 'open sans'> 3. 如有疑问请联系客服热线<a href='tel:4006-788-887'>4006-788-887</a>。<p>" +
                "</body> </html>";
        helper.setText(sb, true);
        FileSystemResource file3 = new FileSystemResource(new File("C:\\Users\\Administrator\\Desktop\\logo.png"));
        helper.addInline("logo",file3);

        // 发送
        mailSender.send(message);
        System.out.println("发送成功！ ");
    }

}

