package com.springboot.controller;
 
import com.springboot.service.MailService;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
 
import org.thymeleaf.context.Context;
 
@RestController
@RequestMapping("/mail")
public class MailController {
 
    @Autowired
    private MailService mailService;
 
    @Autowired
    private TemplateEngine templateEngine;
 
 
    @RequestMapping("/sendSimpleMail")
    public String sendSimpleMail() {
        String to = "719030572@qq.com";
        String subject = "test html mail";
        String content = "hello, this is html mail！";
        mailService.sendSimpleMail(to, subject, content);
        return "success";
    }
 
    @RequestMapping("/sendHtmlMail")
    public String  sendHtmlMail() {
        String to = "719030572@qq.com";
        String subject = "test html mail";
        String content = "hello, this is html mail";
        mailService.sendHtmlMail(to, subject, content);
        return "success";
    }
 
 
 
    @RequestMapping("/sendAttachmentsMail")
    public String sendAttachmentsMail() {
        String filePath="E:\\11111.txt";
        mailService.sendAttachmentMail("719030572@qq.com", "主题：带附件的邮件", "有附件，请查收！", filePath);
        return "success";
    }
 
    @RequestMapping("/sendTemplateMail")
    public String sendTemplateMail() {
        //创建邮件正文
        Context context = new Context();
        context.setVariable("user", "1111");
        context.setVariable("web", "tttt");
        context.setVariable("company", "我是一个公司");
        context.setVariable("product","我是一个产品");
        String emailContent = templateEngine.process("email", context);
        mailService.sendHtmlMail("719030572@qq.com","主题：这是模板邮件",emailContent);
        return "success";
    }
    
    @RequestMapping("/sendCheckCode")
        public String sendCheckCode(HttpServletRequest request) {
            mailService.sendCheckCode(request.getParameter("email"));
            return "success";
        }
    	
  
}