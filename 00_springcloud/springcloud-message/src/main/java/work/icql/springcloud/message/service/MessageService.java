package work.icql.springcloud.message.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author icql
 * @version 1.0
 * @date 2019/1/2 15:22
 * @Title MessageService
 * @Description MessageService
 */
@Service
public class MessageService {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void kk(String usercode, String subject, String content) {
        Map<String, String> map = new HashMap();
        map.put("usercode", usercode);
        map.put("subject", subject);
        map.put("content", content);
        rabbitTemplate.convertAndSend("message", "message-kk", map);
    }

    public void email(String email, String subject, String content) {
        Map<String, String> map = new HashMap();
        map.put("email", email);
        map.put("subject", subject);
        map.put("content", content);
        rabbitTemplate.convertAndSend("message", "message-email", map);
    }

    public void sms(String mobile, String subject, String content) {
        Map<String, String> map = new HashMap();
        map.put("mobile", mobile);
        map.put("subject", subject);
        map.put("content", content);
        rabbitTemplate.convertAndSend("message", "message-sms", map);
    }

    public void all(String usercode, String mobile, String email, String subject, String content) {
        Map<String, String> map = new HashMap();
        map.put("usercode", usercode);
        map.put("mobile", mobile);
        map.put("email", email);
        map.put("subject", subject);
        map.put("content", content);
        rabbitTemplate.convertAndSend("message", "message-kk.message-email.message-sms", map);
    }
}
