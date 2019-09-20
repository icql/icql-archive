package work.icql.springboot.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author icql
 * @version 1.0
 * @date 2018/11/29 11:17
 * @Title SmsListener
 * @Description SmsListener
 */
@Component
@RabbitListener(queues = "message-sms")
public class SmsListener {
    @RabbitHandler
    public void sendSms(Map<String, String> message) {
        System.out.println("手机号：" + message.get("mobile"));
        System.out.println("主题：" + message.get("subject"));
        System.out.println("内容：" + message.get("content"));
        //阿里云短信
        //公司短信平台
    }
}
