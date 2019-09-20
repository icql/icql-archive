package work.icql.ithome.qa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import work.icql.ithome.common.entity.PageResult;
import work.icql.ithome.common.entity.Result;
import work.icql.ithome.common.entity.StatusCode;
import work.icql.ithome.qa.entity.Reply;
import work.icql.ithome.qa.service.ReplyService;

/**
 * @author icql
 * @version 1.0
 * @date 2019/1/25 11:35
 * @Title ReplyController
 * @Description ReplyController
 */
@RestController
@RequestMapping("/reply")
public class ReplyController {
    @Autowired
    private ReplyService replyService;

    @GetMapping
    public Result findAll() {
        return new Result(true, StatusCode.OK, "查询成功", replyService.findAll());
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable String id) {
        return new Result(true, StatusCode.OK, "查询成功", replyService.findById(id));
    }

    @PostMapping
    public Result add(@RequestBody Reply reply) {
        replyService.add(reply);
        return new Result(true, StatusCode.OK, "增加成功");
    }

    @PutMapping("/{id}")
    public Result update(@RequestBody Reply reply, @PathVariable String id) {
        reply.setId(id);
        replyService.update(reply);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable String id) {
        replyService.deleteById(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    @PostMapping("/search/{page}/{size}")
    public Result findSearch(@RequestBody Reply reply, @PathVariable int page, @PathVariable int size) {
        Page pageList = replyService.findSearch(reply, page, size);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<>(pageList.getTotalElements(), pageList.getContent()));
    }
}
