package work.icql.springboot.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author icql
 * @version 1.0
 * @date 2018/11/2 15:12
 * @Title KkListener
 * @Description KkListener
 */
@Component
@RabbitListener(queues = "message-kk")
public class KkListener1 {
    @RabbitHandler
    public void sendEmail(Map<String, String> message) {
        System.out.println("1工号：" + message.get("usercode"));
        System.out.println("1主题：" + message.get("subject"));
        System.out.println("1内容：" + message.get("content"));
        //TODO
    }
}
