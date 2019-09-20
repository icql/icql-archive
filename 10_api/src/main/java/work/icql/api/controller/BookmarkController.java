package work.icql.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import work.icql.api.common.result.Result;
import work.icql.api.common.result.ResultCode;
import work.icql.api.pojo.dto.BookmarkDataResultDTO;
import work.icql.api.pojo.dto.BookmarkDataTreeNodeDTO;
import work.icql.api.service.BookmarkService;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author icql
 * @version 1.0
 * @date 2018/12/3 17:04
 * @Title BookmarkController
 * @Description BookmarkController
 */
@Controller
@RequestMapping("/bookmark")
public class BookmarkController {
    @Autowired
    private BookmarkService bookmarkService;

    @GetMapping
    public String index() {
        return "bookmark/index";
    }

    @GetMapping("data/page")
    public String dataPage(@NotNull String name, Integer version, Model model) {
        String pageData = bookmarkService.getBookmarkPageData(name, version);
        String title = String.format("[%s]的书签%s版", name, (version == null ? "最新" : ("第" + version)));

        model.addAttribute("title", title);
        model.addAttribute("treeHtml", pageData);
        return "bookmark/data";
    }

    @GetMapping("data")
    @ResponseBody
    public Result data(@NotNull String name, Integer version) {
        List<BookmarkDataTreeNodeDTO> bookmarkData = bookmarkService.getBookmarkData(name, version);
        return Result.success(bookmarkData);
    }

    @PostMapping("/sync")
    @ResponseBody
    public Result sync(String token, Integer version, String bookmarkJson) {
        Object o = bookmarkService.sync(token, version == null ? 0 : version, bookmarkJson);
        //无返回值
        if (o == null) {
            return Result.success();
        }
        //返回的是新的书签数据
        if (o instanceof BookmarkDataResultDTO) {
            return Result.success(ResultCode.SUCCESS_ADDITION1, o);
        }
        //返回的是版本号
        return Result.success(ResultCode.SUCCESS_ADDITION2, o);
    }
}
