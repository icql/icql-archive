package work.icql.springboot.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author icql
 * @version 1.0
 * @date 2018/11/2 15:10
 * @Title EmailListener
 * @Description EmailListener
 */
@Component
@RabbitListener(queues = "message-email")
public class EmailListener {
    @RabbitHandler
    public void sendEmail(Map<String, String> message) {
        System.out.println("邮箱地址：" + message.get("email"));
        System.out.println("主题：" + message.get("subject"));
        System.out.println("内容：" + message.get("content"));
        //TODO
    }
}
