package work.icql.ithome.gathering.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import work.icql.ithome.common.entity.PageResult;
import work.icql.ithome.common.entity.Result;
import work.icql.ithome.common.entity.StatusCode;
import work.icql.ithome.gathering.entity.Gathering;
import work.icql.ithome.gathering.service.GatheringService;

/**
 * @author icql
 * @version 1.0
 * @date 2019/1/24 13:23
 * @Title GatheringController
 * @Description GatheringController
 */
@RestController
@RequestMapping("/gathering")
@CrossOrigin
public class GatheringController {
    @Autowired
    private GatheringService gatheringService;

    @GetMapping
    public Result findAll() {
        return new Result(true, StatusCode.OK, "查询成功", gatheringService.findAll());
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable String id) {
        return new Result(true, StatusCode.OK, "查询成功", gatheringService.findById(id));
    }

    @PostMapping
    public Result add(@RequestBody Gathering gathering) {
        gatheringService.add(gathering);
        return new Result(true, StatusCode.OK, "增加成功");
    }

    @PutMapping("/{id}")
    public Result update(@RequestBody Gathering gathering, @PathVariable String id) {
        gathering.setId(id);
        gatheringService.update(gathering);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable String id) {
        gatheringService.deleteById(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    @PostMapping("/search/{page}/{size}")
    public Result findSearch(@RequestBody Gathering gathering, @PathVariable int page, @PathVariable int size) {
        Page pageList = gatheringService.findSearch(gathering, page, size);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<>(pageList.getTotalElements(), pageList.getContent()));
    }
}
