package work.icql.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author icql
 * @version 1.0
 * @date 2018/10/31 15:00
 * @Title HomeController
 * @Description HomeController
 */
@Controller
public class HomeController {
    @RequestMapping("/")
    public String index(Model model) {
        //model.addAttribute("test","测试值");
        return "index";
    }

    @RequestMapping("/{page}")
    public String page(@PathVariable String page) {
        switch (page) {
            case "easyui":
                return "/easyui/index";
            case "kindeditor":
                return "/kindeditor/index";
            case "fastdfs":
                return "/fastdfs/index";
            case "shiro":
                return "/shiro/index";
            case "sso":
                return "/sso/index";
            case "ssoclient":
                return "/ssoclient/index";
            default:
                break;
        }
        return page;
    }
}
