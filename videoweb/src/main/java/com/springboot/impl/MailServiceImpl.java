package com.springboot.impl;
 
import com.springboot.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
 
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
 
 
@Service
@Component
public class MailServiceImpl implements MailService {
 
    @Autowired
    private JavaMailSender mailSender;
 
    //@Value("${mail.fromMail.addr}")
    private String from="719030572@qq.com";
 
    @Override
    public void sendSimpleMail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        try{
 
            mailSender.send(message);
            System.out.println("success");
        }catch (Exception e){
            System.out.println("fail"+e);
        }
    }
 
    @Override
    public void sendHtmlMail(String to, String subject, String content) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);   //true表示需要创建一个multipart message
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);
            mailSender.send(message);
            System.out.println("html邮件发送成功");
        } catch (MessagingException e) {
            System.out.println("发送html邮件时发生异常！"+e);
        }
    }
 
    @Override
    public void sendAttachmentMail(String to, String subject, String content, String filePath) {
        MimeMessage message = mailSender.createMimeMessage();
 
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);
 
            FileSystemResource file = new FileSystemResource(new File(filePath));
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
            helper.addAttachment(fileName, file);
            //helper.addAttachment("test"+fileName, file);
 
            mailSender.send(message);
            System.out.println("带附件的邮件已经发送。");
        } catch (MessagingException e) {
            System.out.println("发送带附件的邮件时发生异常！"+e);
        }
    }
    @Override
    public int sendCheckCode(String to)
    {
    	int check_code=(int )(100000+Math.random()*(999999-1+1));
    	sendSimpleMail(to,"视频网站验证码","您的验证码是: "+check_code+"\n请在十分钟内输入该验证码");
		return check_code;
    	
    	
    }
}