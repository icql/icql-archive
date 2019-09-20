package work.icql.springboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import work.icql.springboot.common.result.Result;
import work.icql.springboot.service.WebSocketService;

/**
 * @author icql
 * @version 1.0
 * @date 2018/11/30 21:09
 * @Title WebSocketServiceImpl
 * @Description WebSocketServiceImpl
 */
@Service
public class WebSocketServiceImpl implements WebSocketService {

    @Value("${websocket.broadcast-path}")
    private String broadcastPath;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Override
    public void sendByBroadcast4Type1(String msg) {
        //广播模式订阅地址：broadcastPath/自定义广播类型名，这里的参数为完整路径
        String destination = broadcastPath + "/type1";
        simpMessagingTemplate.convertAndSend(destination, Result.success(msg));
    }

    @Override
    public void sendByPoint4Type1(String id, String msg) {
        //点对点订阅地址：pointPath/用户唯一id/自定义点对点类型名，这里的参数只需要输入后缀自定义类型名即可
        //注意与广播模式区分
        String destination = "/type1";
        simpMessagingTemplate.convertAndSendToUser(id, destination, Result.success(msg));
    }
}
