package work.icql.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author icql
 * @version 1.0
 * @date 2018/11/17 15:38
 * @Title SSOClientController
 * @Description SSOClientController
 */
@Controller
@RequestMapping("/ssoclient")
public class SSOClientController {
    @RequestMapping("/{page}")
    public String page(@PathVariable String page) {
        return ("ssoclient/" + page);
    }
}
