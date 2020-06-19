package com.springboot.service;

public interface MailService {
    //发送普通邮件
    void sendSimpleMail(String to,String subject,String content);
    //发送Html邮件
    void sendHtmlMail(String to,String subject,String content);
    //发送带附件的邮件
    void sendAttachmentMail(String to,String subject,String content,String filePath);
    int sendCheckCode(String to);
}
