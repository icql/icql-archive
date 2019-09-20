package work.icql.ithome.label.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import work.icql.ithome.common.entity.PageResult;
import work.icql.ithome.common.entity.Result;
import work.icql.ithome.common.entity.StatusCode;
import work.icql.ithome.label.entity.Label;
import work.icql.ithome.label.service.LabelService;

/**
 * @author icql
 * @version 1.0
 * @date 2019/1/24 13:23
 * @Title LabelController
 * @Description LabelController
 */
@RestController
@RequestMapping("/label")
@CrossOrigin
public class LabelController {
    @Autowired
    private LabelService labelService;

    @GetMapping
    public Result findAll() {
        return new Result(true, StatusCode.OK, "查询成功", labelService.findAll());
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable String id) {
        return new Result(true, StatusCode.OK, "查询成功", labelService.findById(id));
    }

    @PostMapping
    public Result add(@RequestBody Label label) {
        labelService.add(label);
        return new Result(true, StatusCode.OK, "增加成功");
    }

    @PutMapping("/{id}")
    public Result update(@RequestBody Label label, @PathVariable String id) {
        label.setId(id);
        labelService.update(label);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable String id) {
        labelService.deleteById(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    @PostMapping("/search/{page}/{size}")
    public Result findSearch(@RequestBody Label label, @PathVariable int page, @PathVariable int size) {
        Page pageList = labelService.findSearch(label, page, size);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<>(pageList.getTotalElements(), pageList.getContent()));
    }
}
