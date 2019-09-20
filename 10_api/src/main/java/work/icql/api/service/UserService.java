package work.icql.api.service;

import work.icql.api.pojo.dto.UserDTO;

/**
 * @author icql
 * @version 1.0
 * @date 2018/12/4 11:35
 * @Title UserService
 * @Description UserService
 */
public interface UserService {

    /**
     * 注册
     *
     * @param userDTO
     */
    void register(UserDTO userDTO);

    /**
     * 激活
     *
     * @param activeCode
     */
    void active(String activeCode);

    /**
     * 重新激活
     *
     * @param userDto
     */
    void reactive(UserDTO userDto);

    /**
     * 找回密码
     *
     * @param userDto
     */
    void getPwd(UserDTO userDto);

    /**
     * 重置密码
     *
     * @param code
     */
    void resetPwd(String code);

    /**
     * 更新信息
     *
     * @param userDTO
     */
    void update(UserDTO userDTO);

    /**
     * 关闭账户
     * @param userDto
     */
    void close(UserDTO userDto);
}
