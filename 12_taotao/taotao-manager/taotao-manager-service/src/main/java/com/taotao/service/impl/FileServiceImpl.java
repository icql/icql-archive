package com.taotao.service.impl;

import com.taotao.common.utils.FastdfsUtilBean;
import com.taotao.common.pojo.KindEditorFileResultBO;
import com.taotao.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private FastdfsUtilBean fastdfsUtilBean = new FastdfsUtilBean();

    @Value("${FILE_BASE_URL}")
    private String file_base_url;

    @Override
    public KindEditorFileResultBO uploadFile(MultipartFile file) {
        KindEditorFileResultBO result = new KindEditorFileResultBO();
        //判断图片是否为空
        if (file.isEmpty()) {
            result.setError(1);
            result.setMessage("图片为空");
            return result;
        }

        //取图片扩展名
        String originalFilename = file.getOriginalFilename();
        String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);

        try {
            String url = file_base_url + "/" + fastdfsUtilBean.upload_file(file.getBytes(), extName);
            result.setError(0);
            result.setUrl(url);
        } catch (Exception e) {
            result.setError(1);
            result.setMessage("图片上传失败" + e.getMessage());
        }

        return result;
    }
}
