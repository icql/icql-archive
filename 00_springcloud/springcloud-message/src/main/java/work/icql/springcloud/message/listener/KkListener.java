package work.icql.springcloud.message.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author icql
 * @version 1.0
 * @date 2019/1/2 15:12
 * @Title KkListener
 * @Description KkListener
 */
@Component
@RabbitListener(queues = "message-kk")
public class KkListener {
    @RabbitHandler
    public void sendEmail(Map<String, String> message) {
        System.out.println("工号：" + message.get("usercode"));
        System.out.println("主题：" + message.get("subject"));
        System.out.println("内容：" + message.get("content"));
        //TODO
    }
}
