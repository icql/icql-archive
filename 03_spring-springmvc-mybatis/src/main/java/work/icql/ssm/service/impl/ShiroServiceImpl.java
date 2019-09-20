package work.icql.ssm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import work.icql.ssm.mapper.TbShiroUserMapper;
import work.icql.ssm.mapper.VwShiroAuthMapper;
import work.icql.ssm.pojo.TbShiroUser;
import work.icql.ssm.pojo.TbShiroUserExample;
import work.icql.ssm.pojo.VwShiroAuth;
import work.icql.ssm.pojo.VwShiroAuthExample;
import work.icql.ssm.service.ShiroService;

import java.util.List;

/**
 * @author icql
 * @version 1.0
 * @date 2018/11/13 15:21
 * @Title ShiroServiceImpl
 * @Description ShiroServiceImpl
 */
@Service
public class ShiroServiceImpl implements ShiroService {
    @Autowired
    private TbShiroUserMapper shiroUserMapper;

    @Autowired
    private VwShiroAuthMapper vwShiroAuthMapper;

    @Override
    public TbShiroUser getShiroUserByUsername(String username) {
        TbShiroUserExample userExample = new TbShiroUserExample();
        TbShiroUserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsernameEqualTo(username.trim());

        List<TbShiroUser> shiroUsers = shiroUserMapper.selectByExample(userExample);
        if (null != shiroUsers && shiroUsers.size() > 0) {
            return shiroUsers.get(0);
        }
        return null;
    }

    @Override
    public List<VwShiroAuth> getShiroFuncByUserName(String userName) {
        VwShiroAuthExample example = new VwShiroAuthExample();
        VwShiroAuthExample.Criteria criteria = example.createCriteria();
        if (!"admin".equals(userName)) {
            criteria.andUsernameEqualTo(userName);
        }
        return vwShiroAuthMapper.selectByExample(example);
    }
}
