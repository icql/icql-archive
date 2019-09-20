package work.icql.ssm.service;


import work.icql.ssm.pojo.TbShiroUser;
import work.icql.ssm.pojo.VwShiroAuth;

import java.util.List;

/**
 * @author icql
 * @version 1.0
 * @date 2018/11/13 14:55
 * @Title ShiroService
 * @Description ShiroService
 */
public interface ShiroService {

    /**
     * 根据用户名获取User
     * @param username
     * @return
     */
    TbShiroUser getShiroUserByUsername(String username);

    /**
     * 根据用户名获取权限
     * @param userName
     * @return
     */
    List<VwShiroAuth> getShiroFuncByUserName(String userName);
}
