

//�ļ��� SendEmail.java

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class SendEmail
{
public static void main(String [] args)
{   
   // �ռ��˵�������
   String to = "abc@abc.com";

   // �����˵�������
   String from = "abc@abc.com";



   // ��ȡϵͳ����
   Properties properties = System.getProperties();

   // �����ʼ�������
   properties.put("mail.smtp.host", "smtp.139.com");
     
   properties.put("mail.smtp.auth", "true");
   properties.put("mail.user", "abc@abc.com");
   properties.put("mail.password", "abc");

   // ��ȡĬ��session����
   Session session = Session.getDefaultInstance(properties);

   try{
      // ����Ĭ�ϵ� MimeMessage ����
      MimeMessage message = new MimeMessage(session);

      // Set From: ͷ��ͷ�ֶ�
      message.setFrom(new InternetAddress(from));

      // Set To: ͷ��ͷ�ֶ�
      message.addRecipient(Message.RecipientType.TO,
                               new InternetAddress(to));

      // Set Subject: ͷ��ͷ�ֶ�
      message.setSubject("This is the Subject Line!");

      // ������Ϣ��
      message.setText("This is actual message");

      // ������Ϣ
      Transport.send(message);
      System.out.println("Sent message successfully....");
   }catch (MessagingException mex) {
      mex.printStackTrace();
   }
}
}