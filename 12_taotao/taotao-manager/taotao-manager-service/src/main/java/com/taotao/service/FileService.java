package com.taotao.service;

import com.taotao.common.pojo.KindEditorFileResultBO;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    KindEditorFileResultBO uploadFile(MultipartFile file);
}
