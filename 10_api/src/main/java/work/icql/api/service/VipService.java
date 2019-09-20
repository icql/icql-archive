package work.icql.api.service;

import work.icql.api.pojo.dto.VipDTO;

import java.util.List;

/**
 * @author icql
 * @version 1.0
 * @date 2018/12/6 13:04
 * @Title VipService
 * @Description VipService
 */
public interface VipService {
    /**
     * 获取主页消息
     *
     * @return
     */
    String getMessage();

    /**
     * 获取使用帮助
     *
     * @return
     */
    String getInstructions();

    /**
     * 获取更新日志
     *
     * @return
     */
    String getUpdateLog();

    /**
     * 获取vip接口
     *
     * @return
     */
    List<String> getApi();

    /**
     * 新增数据
     *
     * @param vipDTO
     */
    void insertData(VipDTO vipDTO);

    /**
     * 刷新缓存
     */
    void refresh();
}
