package work.icql.ithome.recruit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import work.icql.ithome.common.entity.PageResult;
import work.icql.ithome.common.entity.Result;
import work.icql.ithome.common.entity.StatusCode;
import work.icql.ithome.recruit.entity.Recruit;
import work.icql.ithome.recruit.service.RecruitService;

import java.util.List;

/**
 * @author icql
 * @version 1.0
 * @date 2019/1/25 11:35
 * @Title RecruitController
 * @Description RecruitController
 */
@RestController
@RequestMapping("/recruit")
public class RecruitController {
    @Autowired
    private RecruitService recruitService;

    @GetMapping
    public Result findAll() {
        return new Result(true, StatusCode.OK, "查询成功", recruitService.findAll());
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable String id) {
        return new Result(true, StatusCode.OK, "查询成功", recruitService.findById(id));
    }

    @PostMapping
    public Result add(@RequestBody Recruit recruit) {
        recruitService.add(recruit);
        return new Result(true, StatusCode.OK, "增加成功");
    }

    @PutMapping("/{id}")
    public Result update(@RequestBody Recruit recruit, @PathVariable String id) {
        recruit.setId(id);
        recruitService.update(recruit);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable String id) {
        recruitService.deleteById(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    @PostMapping("/search/{page}/{size}")
    public Result findSearch(@RequestBody Recruit recruit, @PathVariable int page, @PathVariable int size) {
        Page pageList = recruitService.findSearch(recruit, page, size);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<>(pageList.getTotalElements(), pageList.getContent()));
    }

    @GetMapping("/search/recommend")
    public Result recommond() {
        List<Recruit> recommend = recruitService.recommend();
        return new Result(true, StatusCode.OK, "查询成功", recommend);
    }

    @GetMapping("/search/newlist")
    public Result newlist() {
        List<Recruit> newlist = recruitService.newlist();
        return new Result(true, StatusCode.OK, "查询成功", newlist);
    }
}
