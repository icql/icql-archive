package work.icql.ssm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import work.icql.ssm.pojo.IcqlResult;
import work.icql.ssm.service.FreemarkService;

/**
 * @author icql
 * @version 1.0
 * @date 2018/11/1 16:56
 * @Title FreemarkerController
 * @Description FreemarkerController
 */
@Controller
@RequestMapping("/freemarker")
public class FreemarkerController {

    @Autowired
    private FreemarkService freemarkService;

    @RequestMapping("/gethtml")
    @ResponseBody
    public IcqlResult getHtml() {
        return freemarkService.getHtml();
    }
}
