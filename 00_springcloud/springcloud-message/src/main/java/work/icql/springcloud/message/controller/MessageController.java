package work.icql.springcloud.message.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import work.icql.springcloud.common.entity.Result;
import work.icql.springcloud.message.service.MessageService;

/**
 * @author icql
 * @version 1.0
 * @date 2019/1/2 15:21
 * @Title MessageController
 * @Description MessageController
 */
@RestController
@CrossOrigin
public class MessageController {
    @Autowired
    private MessageService messageService;

    @RequestMapping("kk")
    public Result kk(String usercode, String subject, String content) {
        messageService.kk(usercode, subject, content);
        return Result.ok();
    }

    @RequestMapping("sms")
    public Result sms(String mobile, String subject, String content) {
        messageService.sms(mobile, subject, content);
        return Result.ok();
    }

    @RequestMapping("email")
    public Result email(String email, String subject, String content) {
        messageService.email(email, subject, content);
        return Result.ok();
    }

    @RequestMapping("all")
    public Result all(String usercode, String mobile, String email, String subject, String content) {
        messageService.all(usercode, mobile, email, subject, content);
        return Result.ok();
    }
}
