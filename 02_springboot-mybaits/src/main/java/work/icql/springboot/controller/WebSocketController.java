package work.icql.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import work.icql.springboot.common.result.Result;
import work.icql.springboot.service.WebSocketService;

/**
 * @author icql
 * @version 1.0
 * @date 2018/11/30 21:11
 * @Title WebSocketController
 * @Description WebSocketController
 */
@Controller
@RequestMapping("/websocket")
@MessageMapping("/websocket")
public class WebSocketController {

    @Autowired
    private WebSocketService webSocketService;

    @MessageMapping("/send")
    public Result send(String msg) {
        String[] msgs = msg.split(",");
        webSocketService.sendByPoint4Type1(msgs[0], msgs[1]);
        return Result.success();
    }

    @MessageMapping("/sendall")
    public Result sendall(String msg) {
        webSocketService.sendByBroadcast4Type1(msg);
        return Result.success();
    }

    /*-----------------------------------测试-------------------------------------------*/

    @GetMapping("/test")
    public String test() {
        return "/websocket/test";
    }

    @GetMapping("/test/send")
    @ResponseBody
    public Result testSend(String id, String msg) {
        webSocketService.sendByPoint4Type1(id, msg);
        return Result.success();
    }

    @GetMapping("/test/sendall")
    @ResponseBody
    public Result testSendall(String msg) {
        webSocketService.sendByBroadcast4Type1(msg);
        return Result.success();
    }
}
