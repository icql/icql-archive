package work.icql.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import work.icql.api.service.JavaIdeService;

/**
 * @author icql
 * @version 1.0
 * @date 2018/12/3 17:05
 * @Title JavaIdeController
 * @Description JavaIdeController
 */
@Controller
@RequestMapping("javaide")
public class JavaIdeController {

    @Autowired
    private JavaIdeService javaIdeService;

    @GetMapping
    public String index() {
        return "javaide/index";
    }
}
