package work.icql.ithome.recruit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import work.icql.ithome.common.entity.PageResult;
import work.icql.ithome.common.entity.Result;
import work.icql.ithome.common.entity.StatusCode;
import work.icql.ithome.recruit.entity.Enterprise;
import work.icql.ithome.recruit.service.EnterpriseService;

import java.util.List;

/**
 * @author icql
 * @version 1.0
 * @date 2019/1/25 11:35
 * @Title EnterpriseController
 * @Description EnterpriseController
 */
@RestController
@RequestMapping("/enterprise")
public class EnterpriseController {
    @Autowired
    private EnterpriseService enterpriseService;

    @GetMapping
    public Result findAll() {
        return new Result(true, StatusCode.OK, "查询成功", enterpriseService.findAll());
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable String id) {
        return new Result(true, StatusCode.OK, "查询成功", enterpriseService.findById(id));
    }

    @PostMapping
    public Result add(@RequestBody Enterprise enterprise) {
        enterpriseService.add(enterprise);
        return new Result(true, StatusCode.OK, "增加成功");
    }

    @PutMapping("/{id}")
    public Result update(@RequestBody Enterprise enterprise, @PathVariable String id) {
        enterprise.setId(id);
        enterpriseService.update(enterprise);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable String id) {
        enterpriseService.deleteById(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    @PostMapping("/search/{page}/{size}")
    public Result findSearch(@RequestBody Enterprise enterprise, @PathVariable int page, @PathVariable int size) {
        Page pageList = enterpriseService.findSearch(enterprise, page, size);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<>(pageList.getTotalElements(), pageList.getContent()));
    }

    @GetMapping("/search/hotlist")
    public Result hotlist() {
        List<Enterprise> hotlist = enterpriseService.hotlist();
        return new Result(true, StatusCode.OK, "查询成功", hotlist);
    }
}
