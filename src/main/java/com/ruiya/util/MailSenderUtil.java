package com.ruiya.util;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Date;


/**
 * 邮件发送的原理：
 * 1 邮箱准备工作，下面以qq邮箱做示例，其他邮箱同理
 * 1.1 登录邮箱进入找到设置
 * 1.2 找到邮箱中的POP3/IMAP/SMTP服务设置，并开启POP3/SMTP,IMAP/SMTP服务 已开启， 此时，邮箱设置已经完成。
 * 1.3 最后查看qq邮箱POP3和SMTP服务器
 *   qq.com   pop.qq.com    stmp.qq.com
 * 注：如需了解POP3、SMTP、IMAP协议，可以查看第三部分。
 *  2 代码部分：
 * 2.1 搭建spring boot项目，引入spring-boot-starter-mail依赖
 *     org.springframework.boot  spring-boot-starter-mail
 * 2.2 配置application.properties中的邮件配置
 * spring.mail.host=smtp.zlfund.cn
 * spring.mail.port=25
 * spring.mail.username=service@zlfund.cn
 * spring.mail.password=zl*20151201
 * spring.mail.default-encoding=UTF-8
 *
 * 2.3 编写发送邮件服务及其实现类(通过spring boot mail 提供的JavaMailSender)
 * import java.io.ByteArrayOutputStream;
 * public interface MailService {
 * /**     * 发送邮件
 * * @param to 目的地
 * * @param subject 主题
 * * @param content 内容
 * * @param os 附件
 * * @param attachmentFilename 附件名
 * * @throws Exception
  * public void sendMail(String to,
 * String subject,String content,
 * ByteArrayOutputStream os,
 * String attachmentFilename,
 * Boolean isHtml)throws  Exception;
 * @author admin
 */

/***
 * 邮件相关类
 * MimeMessage类继承自Message抽象类。同时扩展了Message抽象类的还有
 * IMAPMessage，IMAPNestedMessage，POP3Message，SMTPMessage，SmartMimeMessage
 *
 * MimeMessage：  MimeMessage代表的是MIME
 * （多用途互联网邮件扩展，Multipurpose Internet Mail Extensions）
 * 格式的邮件消息，在本次演示的Demo中使用的就是MIME。
 *
 * SMTPMessage ：
 * SMTP（Simple Mail Transfer Protocol）即简单邮件传输协议。
 * SMTP 认证，简单地说就是要求必须在提供了账户名和密码之后才可以登录 SMTP 服务器，
 * 这就使得那些垃圾邮件的散播者无可乘之机。在Spring Boot Starter Mail的自动配置属性文件类中，
 * 默认使用的是该协议。
 *
 * POP3Message：
 * POP3（Post Office Protocol 3）即邮局协议的第3个版本，具体的介绍可以参考维基百科。
 *
 * IMAPMessage
 * IMAP（Internet Mail Access Protocol）即交互式邮件存取协议，
 * 它是跟POP3类似邮件访问标准协议之一。不同的是，开启了IMAP后，
 * 您在电子邮件客户端收取的邮件仍然保留在服务器上，同时在客户端上的操作都会反馈到服务器上。
 *  // 发件人地址
 *  private String from;
 *
 *    private String replyTo;
 *  // 收件人地址
 *    private String[] to;
 *  抄送地址
 *  mailMessage.setRecipients(Message.RecipientType.CC, cc);
 *    private String[] cc;
 *
 *   密送地址
 *  InternetAddress[] bcc = new InternetAddress().parse(mailInfo.getBccAddress());
 *  mailMessage.setRecipients(Message.RecipientType.BCC, bcc);
 *    private String[] bcc;
 *
 *    设置邮件发送的时间
 *    private Date sentDate;
 *
 *    设置邮件的主题
 *    private String subject;
 *
 *    @Nullable
 *    private String text;
 *
 */
public class MailSenderUtil {

        @Autowired
        private JavaMailSender mailSender;

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
    @SneakyThrows
    public void sendMailAttachmentSenderTest(){
            // 消息构造器
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            //发件人 设置昵称
//        helper.setFrom("service@zlfund.cn");
            helper.setFrom(new InternetAddress("service@zlfund.cn", "众禄基金", "UTF-8"));
            // 收件人 设置昵称
            helper.setTo(new InternetAddress("liu_yaxian@126.com", "刘亚仙", "UTF-8"));
//            helper.setReplyTo(new InternetAddress("liu_yaxian@126.com", "刘亚仙", "UTF-8"));

        // 发送日期
            helper.setSentDate(new Date());
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

    @SneakyThrows
    public void sendMail(String receivers, String subject, String htmlMsg){
        MimeMessage mail = mailSender.createMimeMessage();
        mail.addRecipients(Message.RecipientType.TO, InternetAddress.parse(receivers));
        //todo replace it with real sender
        mail.setFrom(new InternetAddress("******"));
        mail.setSentDate(new Date());
        mail.setSubject(subject);

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(htmlMsg, "text/html;charset=utf-8");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);
        mail.setContent(multipart);
        mailSender.send(mail);
    }

}
