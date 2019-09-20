package work.icql.springboot.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import work.icql.springboot.common.result.Result;
import work.icql.springboot.entity.User;
import work.icql.springboot.service.UserService;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author icql
 * @version 1.0
 * @date 2018/11/28 14:28
 * @Title UserController
 * @Description UserController
 */
@Api("Users接口文档")
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation("查询用户")
    @GetMapping("/{id}")
    public Result getUser(@NotNull @PathVariable Long id) {
        User user = userService.getUser(id);
        return Result.success(user);
    }

    @ApiOperation("查询用户-批量(分页)")
    @GetMapping
    public Result listUser(@RequestParam(name = "pageNum") @NotNull Integer pageNum, @RequestParam(name = "pageSize") @NotNull Integer pageSize, String username) {
        PageInfo pageInfo = userService.listUser(pageNum, pageSize, username);
        return Result.success(pageInfo);
    }

    @ApiOperation("新增用户")
    @PostMapping
    public Result insertUser(@RequestBody User user) {
        userService.insertUser(user);
        return Result.success();
    }

    @ApiOperation("新增用户-批量")
    @PostMapping("/batch")
    public Result insertUser(@RequestBody List<User> users) {
        userService.insertUser(users);
        return Result.success();
    }

    @ApiOperation(value = "更新用户(全量)")
    @PutMapping
    public Result updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return Result.success();
    }

    @ApiOperation(value = "更新用户(全量)-批量")
    @PutMapping("/batch")
    public Result updateUser(@RequestBody List<User> users) {
        userService.updateUser(users);
        return Result.success();
    }

    @ApiOperation(value = "更新用户(选择性)")
    @PatchMapping
    public Result updateUserSelective(@RequestBody User user) {
        userService.updateUserSelective(user);
        return Result.success();
    }

    @ApiOperation(value = "更新用户(选择性)-批量")
    @PatchMapping("/batch")
    public Result updateUserSelective(@RequestBody List<User> users) {
        userService.updateUserSelective(users);
        return Result.success();
    }

    @ApiOperation(value = "删除用户")
    @DeleteMapping("/{id}")
    public Result deleteUser(@NotNull @PathVariable Long id) {
        userService.deleteUser(id);
        return Result.success();
    }

    @ApiOperation(value = "删除用户-批量")
    @DeleteMapping
    public Result deleteUser(@ApiParam("用户ids字符串，用半角,分割") @RequestParam(name = "ids") @NotNull String ids) {
        userService.deleteUser(ids);
        return Result.success();
    }
}
