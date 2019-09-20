package work.icql.ithome.qa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import work.icql.ithome.common.entity.PageResult;
import work.icql.ithome.common.entity.Result;
import work.icql.ithome.common.entity.StatusCode;
import work.icql.ithome.qa.entity.Problem;
import work.icql.ithome.qa.service.ProblemService;

/**
 * @author icql
 * @version 1.0
 * @date 2019/1/25 11:35
 * @Title ProblemController
 * @Description ProblemController
 */
@RestController
@RequestMapping("/problem")
public class ProblemController {
    @Autowired
    private ProblemService problemService;

    @GetMapping
    public Result findAll() {
        return new Result(true, StatusCode.OK, "查询成功", problemService.findAll());
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable String id) {
        return new Result(true, StatusCode.OK, "查询成功", problemService.findById(id));
    }

    @PostMapping
    public Result add(@RequestBody Problem problem) {
        problemService.add(problem);
        return new Result(true, StatusCode.OK, "增加成功");
    }

    @PutMapping("/{id}")
    public Result update(@RequestBody Problem problem, @PathVariable String id) {
        problem.setId(id);
        problemService.update(problem);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable String id) {
        problemService.deleteById(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    @PostMapping("/search/{page}/{size}")
    public Result findSearch(@RequestBody Problem problem, @PathVariable int page, @PathVariable int size) {
        Page pageList = problemService.findSearch(problem, page, size);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<>(pageList.getTotalElements(), pageList.getContent()));
    }

    @GetMapping("/newlist/{label}/{page}/{size}")
    public Result newlist(@PathVariable String labelid, @PathVariable int page, @PathVariable int size) {
        Page<Problem> newlist = problemService.newlist(labelid, page, size);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<>(newlist.getTotalElements(), newlist.getContent()));
    }

    @GetMapping("/hotlist/{label}/{page}/{size}")
    public Result hotlist(@PathVariable String labelid, @PathVariable int page, @PathVariable int size) {
        Page<Problem> hotlist = problemService.hotlist(labelid, page, size);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<>(hotlist.getTotalElements(), hotlist.getContent()));
    }

    @GetMapping("/waitlist/{label}/{page}/{size}")
    public Result waitlist(@PathVariable String labelid, @PathVariable int page, @PathVariable int size) {
        Page<Problem> waitlist = problemService.waitlist(labelid, page, size);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<>(waitlist.getTotalElements(), waitlist.getContent()));
    }
}
