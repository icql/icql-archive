package work.icql.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import work.icql.ssm.pojo.KindEditorFile;

/**
 * @author icql
 * @version 1.0
 * @date 2018/11/10 9:32
 * @Title kindeditorController
 * @Description kindeditorController
 */
@Controller
@RequestMapping("/kindeditor")
public class KindeditorController {

    @RequestMapping("/{page}")
    public String page(@PathVariable String page) {
        page = "kindeditor/" + page;
        return page;
    }

    @RequestMapping("/api/upload")
    @ResponseBody
    public KindEditorFile upload(MultipartFile file) {
        KindEditorFile kindEditorFile = new KindEditorFile();
        kindEditorFile.setError(0);//0/1：成功/失败
        kindEditorFile.setUrl("https://www.baidu.com/img/baidu_jgylogo3.gif");
        return kindEditorFile;
    }

    @RequestMapping("/api/form")
    public String form(String desc) {
        String value = desc;
        return "redirect:/kindeditor";
    }
}
