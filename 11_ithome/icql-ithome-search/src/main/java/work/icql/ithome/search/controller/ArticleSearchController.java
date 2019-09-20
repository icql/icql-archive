package work.icql.ithome.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import work.icql.ithome.common.entity.PageResult;
import work.icql.ithome.common.entity.Result;
import work.icql.ithome.common.entity.StatusCode;
import work.icql.ithome.search.entity.ArticleSearch;
import work.icql.ithome.search.service.ArticleSearchService;

/**
 * @author icql
 * @version 1.0
 * @date 2019/1/28 15:38
 * @Title ArticleSearchController
 * @Description ArticleSearchController
 */
@RestController
@RequestMapping("/articlesearch")
public class ArticleSearchController {
    @Autowired
    private ArticleSearchService articleSearchService;

    @PostMapping
    public Result save(@RequestBody ArticleSearch articleSearch) {
        articleSearchService.save(articleSearch);
        return new Result(true, StatusCode.OK, "操作成功");
    }
    
    @GetMapping("/search/{keywords}/{page}/{size}")
    public Result findByTitleLike(@PathVariable String keywords, @PathVariable int page, @PathVariable int size) {
        Page<ArticleSearch> articlePage = articleSearchService.findByTitleLike(keywords, page, size);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<ArticleSearch>(articlePage.getTotalElements(), articlePage.getContent()));
    }
}
