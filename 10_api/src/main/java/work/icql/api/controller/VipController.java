package work.icql.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import work.icql.api.common.result.Result;
import work.icql.api.pojo.dto.VipDTO;
import work.icql.api.service.VipService;

import java.util.List;

/**
 * @author icql
 * @version 1.0
 * @date 2018/12/3 17:05
 * @Title VipController
 * @Description VipController
 */
@Controller
@RequestMapping("vip")
@Validated
public class VipController {

    @Autowired
    private VipService vipService;

    @GetMapping
    public String index() {
        return "vip/index";
    }

    @GetMapping("/message")
    @ResponseBody
    public Result message() {
        String message = vipService.getMessage();
        return Result.success(message);
    }

    @GetMapping("/instructions")
    @ResponseBody
    public Result instructions() {
        String instructions = vipService.getInstructions();
        return Result.success(instructions);
    }

    @GetMapping("/updatelog")
    @ResponseBody
    public Result updateLog() {
        String updateLog = vipService.getUpdateLog();
        return Result.success(updateLog);
    }

    @GetMapping("/api")
    @ResponseBody
    public Result api() {
        List<String> api = vipService.getApi();
        return Result.success(api);
    }

    @PostMapping
    @ResponseBody
    public Result insertData(@RequestBody @Validated VipDTO vipDTO) {
        vipService.insertData(vipDTO);
        return Result.success();
    }

    @GetMapping("/refresh")
    @ResponseBody
    public Result refresh() {
        vipService.refresh();
        return Result.success();
    }
}
