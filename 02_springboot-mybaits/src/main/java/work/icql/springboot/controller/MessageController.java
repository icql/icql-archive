package work.icql.springboot.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import work.icql.springboot.common.result.Result;
import work.icql.springboot.service.MessageService;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author icql
 * @version 1.0
 * @date 2018/11/29 15:21
 * @Title MessageController
 * @Description MessageController
 */
@Api("Message接口文档")
@RestController
@RequestMapping("/messages")
@Validated
public class MessageController {
    @Autowired
    private MessageService messageService;

    @GetMapping("kk")
    public Result kk(@RequestParam("usercode") @NotNull @Pattern(regexp = "^[aA]\\d{4}$") String usercode,
                     @RequestParam("subject") @NotNull String subject,
                     @RequestParam("content") @NotNull String content) {
        messageService.kk(usercode, subject, content);
        return Result.success();
    }

    @GetMapping("sms")
    public Result sms(@RequestParam("mobile") @NotNull @Pattern(regexp = "1[345789]\\d{9}") String mobile,
                      @RequestParam("subject") @NotNull String subject,
                      @RequestParam("content") @NotNull String content) {
        messageService.sms(mobile, subject, content);
        return Result.success();
    }

    @GetMapping("email")
    public Result email(@RequestParam("email") @NotNull @Email String email,
                        @RequestParam("subject") @NotNull String subject,
                        @RequestParam("content") @NotNull String content) {
        messageService.email(email, subject, content);
        return Result.success();
    }

    @GetMapping("all")
    public Result all(
            @RequestParam("usercode") @NotNull @Pattern(regexp = "^[aA]\\d{4}$") String usercode,
            @RequestParam("mobile") @NotNull @Pattern(regexp = "1[345789]\\d{9}") String mobile,
            @RequestParam("email") @NotNull @Email String email,
            @RequestParam("subject") @NotNull String subject,
            @RequestParam("content") @NotNull String content) {
        messageService.all(usercode, mobile, email, subject, content);
        return Result.success();
    }
}
