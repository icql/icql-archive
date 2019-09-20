package work.icql.springboot.service.impl;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import work.icql.springboot.service.MessageService;

import java.util.HashMap;
import java.util.Map;

/**
 * @author icql
 * @version 1.0
 * @date 2018/11/2 15:22
 * @Title MessageService
 * @Description MessageService
 */
@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void kk(String usercode, String subject, String content) {
        Map<String, String> map = new HashMap();
        map.put("usercode", usercode);
        map.put("subject", subject);
        map.put("content", content);
        rabbitTemplate.convertAndSend("message", "message-kk", map);
    }

    @Override
    public void email(String email, String subject, String content) {
        Map<String, String> map = new HashMap();
        map.put("email", email);
        map.put("subject", subject);
        map.put("content", content);
        rabbitTemplate.convertAndSend("message", "message-email", map);
    }

    @Override
    public void sms(String mobile, String subject, String content) {
        Map<String, String> map = new HashMap();
        map.put("mobile", mobile);
        map.put("subject", subject);
        map.put("content", content);
        rabbitTemplate.convertAndSend("message", "message-sms", map);
    }

    @Override
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
