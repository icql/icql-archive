package work.icql.ssm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import work.icql.javatutil.FastdfsClient;
import work.icql.ssm.pojo.IcqlResult;
import work.icql.ssm.service.FastdfsService;

import java.io.IOException;

/**
 * @author icql
 * @version 1.0
 * @date 2018/11/11 14:57
 * @Title FastdfsServiceImpl
 * @Description TODO
 */
@Service
public class FastdfsServiceImpl implements FastdfsService {

    @Autowired
    private FastdfsClient fastdfsClient;

    @Override
    public IcqlResult upload(MultipartFile file) {
        IcqlResult result = null;
        if (file.isEmpty()) {
            result = IcqlResult.build(204, "文件为空！");
            return result;
        }
        String originalFilename = file.getOriginalFilename();
        String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        try {
            String upload = fastdfsClient.upload(file.getBytes(), extName);
            if (StringUtils.isEmpty(upload)) {
                result = IcqlResult.build(500, "服务器出错");
            } else {
                result = IcqlResult.ok();
            }
        } catch (IOException e) {
            e.printStackTrace();
            result = IcqlResult.build(500, e.getMessage());
        }

        return result;
    }
}
