package work.icql.ithome.spit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import work.icql.ithome.common.entity.PageResult;
import work.icql.ithome.common.entity.Result;
import work.icql.ithome.common.entity.StatusCode;
import work.icql.ithome.spit.entity.Spit;
import work.icql.ithome.spit.service.SpitService;

/**
 * @author icql
 * @version 1.0
 * @date 2019/1/28 10:31
 * @Title SpitController
 * @Description SpitController
 */
@RestController
@RequestMapping("/spit")
public class SpitController {
    @Autowired
    private SpitService spitService;

    @GetMapping
    public Result findAll() {
        return new Result(true, StatusCode.OK, "查询成功", spitService.findAll());
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable String id) {
        return new Result(true, StatusCode.OK, "查询成功", spitService.findById(id));
    }

    @PostMapping
    public Result add(@RequestBody Spit spit) {
        spitService.add(spit);
        return new Result(true, StatusCode.OK, "增加成功");
    }

    @PutMapping("/{id}")
    public Result update(@RequestBody Spit spit, @PathVariable String id) {
        spit.set_id(id);
        spitService.update(spit);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable String id) {
        spitService.deleteById(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    @GetMapping("/comment/{parentId}/{page}/{size}")
    public Result findSearch(@PathVariable String parentId, @PathVariable int page, @PathVariable int size) {
        Page pageList = spitService.findByParentid(parentId, page, size);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<>(pageList.getTotalElements(), pageList.getContent()));
    }

    @PutMapping(value = "/thumbup/{id}/{userid}")
    public Result updateThumbup(@PathVariable String id, @PathVariable String userid) {
        return spitService.updateThumbup(id, userid);
    }
}
