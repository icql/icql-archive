package work.icql.ssm.service;

import org.springframework.web.multipart.MultipartFile;
import work.icql.ssm.pojo.IcqlResult;

/**
 * @author icql
 * @version 1.0
 * @date 2018/11/11 14:54
 * @Title FastdfsService
 * @Description TODO
 */
public interface FastdfsService {

    /**
     * 上传文件
     * @param file
     * @return
     */
    IcqlResult upload(MultipartFile file);
}
