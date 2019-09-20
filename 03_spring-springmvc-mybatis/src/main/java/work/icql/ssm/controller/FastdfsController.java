package work.icql.ssm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import work.icql.ssm.pojo.IcqlResult;
import work.icql.ssm.service.FastdfsService;

@Controller
@RequestMapping("/fastdfs")
public class FastdfsController {

    @Autowired
    private FastdfsService fastdfsService;

    @RequestMapping("/{page}")
    public String page(@PathVariable String page) {
        return ("fastdfs/" + page);
    }

    @RequestMapping("/api/upload")
    @ResponseBody
    public IcqlResult upload(MultipartFile file) {
        return fastdfsService.upload(file);
    }

}
