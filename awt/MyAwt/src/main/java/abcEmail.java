

/**
 * original version
 *
 //文件名 SendEmail.java

 import java.util.*;
 import javax.mail.*;
 import javax.mail.internet.*;
 import javax.activation.*;

 public class SendEmail
 {
 public static void main(String [] args)
 {
 // 收件人电子邮箱
 String to = "abc@abc.com";

 // 发件人电子邮箱
 String from = "abc@abc.com";



 // 获取系统属性
 Properties properties = System.getProperties();

 // 设置邮件服务器
 properties.put("mail.smtp.host", "smtp.139.com");

 properties.put("mail.smtp.auth", "true");
 properties.put("mail.user", "abc@abc.com");
 properties.put("mail.password", "abc");

 // 获取默认session对象
 Session session = Session.getDefaultInstance(properties);

 try{
 // 创建默认的 MimeMessage 对象
 MimeMessage message = new MimeMessage(session);

 // Set From: 头部头字段
 message.setFrom(new InternetAddress(from));

 // Set To: 头部头字段
 message.addRecipient(Message.RecipientType.TO,
 new InternetAddress(to));

 // Set Subject: 头部头字段
 message.setSubject("This is the Subject Line!");

 // 设置消息体
 message.setText("This is actual message");

 // 发送消息
 Transport.send(message);
 System.out.println("Sent message successfully....");
 }catch (MessagingException mex) {
 mex.printStackTrace();
 }
 }
 }
 *
 */