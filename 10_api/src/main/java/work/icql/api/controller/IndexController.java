package work.icql.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author icql
 * @version 1.0
 * @date 2018/12/8 15:53
 * @Title IndexController
 * @Description IndexController
 */
@Controller
public class IndexController {
    @RequestMapping("/")
    public String index() {
        return "index";
    }
}
