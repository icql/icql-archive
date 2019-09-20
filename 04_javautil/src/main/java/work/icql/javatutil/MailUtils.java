package work.icql.javatutil;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public final class MailUtils {

    private MailUtils() {
    }

    /**
     * 发送QQ邮件
     *
     * @param from         发件邮箱 [test4icql@qq.com]
     * @param fromPassword 发件邮箱密码 [hndxruqqjqwihijf]
     * @param to           收件邮箱
     * @param subject      主题
     * @param content      内容
     * @return
     */
    public static boolean sendQQMail(String from, String fromPassword, String to, String subject, String content) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.qq.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.user", from);
        props.put("mail.password", fromPassword);

        // 构建授权信息，用于进行SMTP进行身份验证
        Authenticator authenticator = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                String userName = props.getProperty("mail.user");
                String password = props.getProperty("mail.password");
                return new PasswordAuthentication(userName, password);
            }
        };
        Session mailSession = Session.getInstance(props, authenticator);// 使用环境属性和授权信息，创建邮件会话
        try {
            MimeMessage message = new MimeMessage(mailSession);// 创建邮件消息
            message.setFrom(new InternetAddress(props.getProperty("mail.user")));// 设置发件人

            message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));// 设置收件人的邮箱
            message.setSubject(subject);// 设置邮件标题
            message.setContent(content, "text/html;charset=UTF-8");// 设置邮件的内容体

            Transport.send(message);// 发送邮件
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
