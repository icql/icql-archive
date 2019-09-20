package work.icql.springboot.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import work.icql.springboot.common.exception.ServiceException;
import work.icql.springboot.entity.User;
import work.icql.springboot.entity.UserExample;
import work.icql.springboot.mapper.UserMapper;
import work.icql.springboot.service.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author icql
 * @version 1.0
 * @date 2019/3/28 14:25
 * @Title UserServiceImpl
 * @Description UserServiceImpl
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    CacheManager cacheManager;

    @Cacheable(cacheNames = "users_id#1800")
    @Override
    public User getUser(long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Cacheable(cacheNames = "users")
    @Override
    public PageInfo listUser(int pageNum, int pageSize, String username) {
        PageHelper.startPage(pageNum, pageSize);

        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(username)) {
            criteria.andUsernameLike("%" + username.trim() + "%");
        }

        List<User> users = userMapper.selectByExample(example);

        return new PageInfo(users);
    }

    @Transactional
    @Override
    public void insertUser(User user) {
        userMapper.insert(user);
    }

    @Override
    public void insertUser(List<User> users) {
        int count = userMapper.insertBatch(users);
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void updateUser(List<User> users) {

    }

    @Override
    public void updateUserSelective(User user) {

    }

    @Override
    public void updateUserSelective(List<User> users) {

    }

    @Override
    public void deleteUser(long id) {

    }

    @Override
    public void deleteUser(String ids) {
        String[] idsStr = ids.split(",");
        List<Long> idsLong = new ArrayList<>();
        for (String id : idsStr) {
            idsLong.add(Long.valueOf(id));
        }
    }
}
