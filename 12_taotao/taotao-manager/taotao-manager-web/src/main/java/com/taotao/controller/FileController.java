package com.taotao.controller;

import com.taotao.common.pojo.KindEditorFileResultBO;
import com.taotao.common.utils.JsonUtils;
import com.taotao.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/file")
public class FileController {
    @Autowired
    private FileService fileService;

    @RequestMapping("/upload")
    @ResponseBody
    public String uploadFile(MultipartFile uploadFile){
        KindEditorFileResultBO kindEditorFileResult = new KindEditorFileResultBO();
        kindEditorFileResult.setError(0);
        kindEditorFileResult.setUrl("https://www.baidu.com/img/baidu_jgylogo3.gif");
        return JsonUtils.objectToJson(kindEditorFileResult);
    }

}
