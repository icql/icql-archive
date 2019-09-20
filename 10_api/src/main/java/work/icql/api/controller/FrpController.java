package work.icql.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author icql
 * @version 1.0
 * @date 2019/6/12 10:25
 * @Title FrpController
 * @Description FrpController
 */
@Controller
@RequestMapping("frp")
public class FrpController {
    @GetMapping
    public String index() {
        return "frp/index";
    }

    @GetMapping("/admin")
    public String admin() {
        return "redirect:http://api.icql.work:57001";
    }
}
