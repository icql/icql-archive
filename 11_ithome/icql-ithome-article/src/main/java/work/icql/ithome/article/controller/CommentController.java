package work.icql.ithome.article.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import work.icql.ithome.article.entity.Article;
import work.icql.ithome.article.entity.Comment;
import work.icql.ithome.article.service.CommentService;
import work.icql.ithome.common.entity.Result;
import work.icql.ithome.common.entity.StatusCode;

/**
 * @author icql
 * @version 1.0
 * @date 2019/1/28 11:28
 * @Title CommentController
 * @Description CommentController
 */
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping
    public Result save(@RequestBody Comment comment) {
        commentService.add(comment);
        return new Result(true, StatusCode.OK, "提交成功 ");
    }

    @PostMapping
    public Result add(@RequestBody Comment comment) {
        commentService.add(comment);
        return new Result(true, StatusCode.OK, "增加成功");
    }

    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable String id) {
        commentService.deleteById(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    @GetMapping("/article/{articleid}")
    public Result findByArticleid(@PathVariable String articleid) {
        return new Result(true, StatusCode.OK, "查询成功", commentService.findByArticleid(articleid));
    }
}
