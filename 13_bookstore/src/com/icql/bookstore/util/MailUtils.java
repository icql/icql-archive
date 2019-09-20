package com.icql.bookstore.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class MailUtils {

    /**
     * 发送邮件
     *
     * @param to      发送的邮件地址
     * @param subject 邮件主题
     * @param content 邮件内容
     * @return
     */
    public static boolean sendMail(String to, String subject, String content) {

        String path = MailUtils.class.getResource("").toString();
        path = path.replace("file:/", "").replace("classes/com/icql/bookstore/util/",
                "prop/mail.properties").replace("/",
                File.separator);

        Properties props = new Properties();
        try{
            InputStream in = new FileInputStream(path);
            props.load(in);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

        // 构建授权信息，用于进行SMTP进行身份验证
        Authenticator authenticator = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                String userName = props.getProperty("mail.user");
                String password = props.getProperty("mail.password");
                return new PasswordAuthentication(userName, password);
            }
        };


        Session mailSession = Session.getInstance(props, authenticator);// 使用环境属性和授权信息，创建邮件会话

        try{
            MimeMessage message = new MimeMessage(mailSession);// 创建邮件消息
            message.setFrom(new InternetAddress(props.getProperty("mail.user")));// 设置发件人

            message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));// 设置收件人的邮箱
            message.setSubject(subject);// 设置邮件标题
            message.setContent(content, "text/html;charset=UTF-8");// 设置邮件的内容体

            Transport.send(message);// 发送邮件
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
