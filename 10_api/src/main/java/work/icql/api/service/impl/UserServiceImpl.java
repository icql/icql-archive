package work.icql.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import work.icql.api.common.exception.ServiceException;
import work.icql.api.common.exception.auth.AuthLoginErrorException;
import work.icql.api.common.exception.data.DataIsExistedException;
import work.icql.api.common.exception.data.DataIsInvalidException;
import work.icql.api.common.exception.data.DataIsNoneException;
import work.icql.api.common.util.IdWorker;
import work.icql.api.common.util.MailUtils;
import work.icql.api.common.util.MsgDigestUtils;
import work.icql.api.mapper.UserMapper;
import work.icql.api.pojo.converter.UserConverter;
import work.icql.api.pojo.dto.UserDTO;
import work.icql.api.pojo.entity.UserDO;
import work.icql.api.service.UserService;

import javax.mail.MessagingException;
import java.util.UUID;

/**
 * @author icql
 * @version 1.0
 * @date 2018/12/4 11:35
 * @Title UserServiceImpl
 * @Description UserServiceImpl
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    @Qualifier("cacheTemplate4expired")
    private Cache cacheTemplate4expired;

    @Value("${mail.username}")
    private String mailFrom;

    @Value("${mail.password}")
    private String mailPassword;

    private final String activeCodeKeyPrefix = "api:user:activecode:";

    @Override
    public void register(UserDTO userDTO) {
        UserDO userDO = userConverter.dto2do(userDTO);

        //验证邮箱或用户名是否存在
        UserDO checkUser = userMapper.selectByNameOrEmail(userDO.getName(), userDO.getEmail());
        if (checkUser != null) {
            throw new DataIsExistedException("用户名或邮箱已存在");
        }

        //新增用户数据
        Long id = idWorker.nextId();
        userDO.setId(id);
        //密码加密,加密后的密码
        String newPassword = encoder.encode(userDO.getPassword());
        userDO.setPassword(newPassword);
        userMapper.insert(userDO);

        sendActiveCode(userDO);
    }

    @Override
    public void active(String activeCode) {
        String key = activeCodeKeyPrefix + activeCode;

        Object id = cacheTemplate4expired.get(key);
        if (id != null) {
            id = ((Cache.ValueWrapper) id).get();
        }

        if (!StringUtils.isEmpty(id)) {
            UserDO userDO = new UserDO();
            userDO.setId(Long.valueOf(id.toString()));
            userDO.setIsDeleted(Byte.valueOf("0"));
            userMapper.updateByPrimaryKeySelective(userDO);
            //删除激活码
            cacheTemplate4expired.evict(key);
        } else {
            throw new DataIsNoneException("激活码已失效");
        }
    }

    @Override
    public void reactive(UserDTO userDto) {
        UserDO user = userMapper.selectByNameOrEmail(userDto.getName(), userDto.getEmail());
        if (user == null) {
            throw new DataIsNoneException("用户名或邮箱不存在");
        }
        Byte deleteFlag = Byte.valueOf("0");
        if (deleteFlag.equals(user.getIsDeleted())) {
            throw new DataIsExistedException("用户已被激活");
        }
        sendActiveCode(user);
    }

    @Override
    public void getPwd(UserDTO userDto) {
        UserDO user = userMapper.selectByNameOrEmail(userDto.getName(), userDto.getEmail());
        if (user == null) {
            throw new DataIsNoneException("用户名或邮箱不存在");
        }

        String id = user.getId().toString();
        String uuid = UUID.randomUUID().toString();
        cacheTemplate4expired.put(uuid, id);

        //发送邮件：新密码
        String mailContent = String.format(
                "ICQL API: 请您点击或复制访问链接 <a href='https://api.icql.work/user/resetpwd?code=%s' style='color:red'>" +
                        "http://api.icql.work/user/resetpwd?code=%s</a> 来重置密码。", uuid, uuid);
        sendMail(user.getEmail(), mailContent);
    }

    @Override
    public void resetPwd(String code) {
        Object id = cacheTemplate4expired.get(code);
        if (id != null) {
            id = ((Cache.ValueWrapper) id).get();
        } else {
            throw new DataIsNoneException("激活码已失效");
        }
        UserDO user = userMapper.selectByPrimaryKey(Long.valueOf(id.toString()));
        String password = UUID.randomUUID().toString().substring(0, 8);
        //加密后的密码
        String newPassword = encoder.encode(password);
        user.setPassword(newPassword);
        userMapper.updateByPrimaryKeySelective(user);

        //发送邮件：新密码
        String mailContent = String.format(
                "ICQL API: 您的新密码为：%s ，请尽快更改密码。", password);
        sendMail(user.getEmail(), mailContent);
    }

    @Override
    public void update(UserDTO userDTO) {
        UserDO user = userMapper.selectByNameOrEmail(userDTO.getName(), userDTO.getEmail());
        if (user == null) {
            throw new DataIsNoneException("用户名或邮箱不存在");
        }
        if (!encoder.matches(userDTO.getTemp(), user.getPassword())) {
            throw new AuthLoginErrorException("旧密码错误");
        }
        //加密后的密码
        String newPassword = encoder.encode(userDTO.getPassword());
        user.setPassword(newPassword);

        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public void close(UserDTO userDto) {
        UserDO user = userMapper.selectByNameOrEmail(userDto.getName(), userDto.getEmail());
        if(!encoder.matches(userDto.getPassword(),user.getPassword())){
            throw new DataIsInvalidException("输入的密码不正确");
        }
        //逻辑删除，修改删除字段为1
        user.setIsDeleted(Byte.valueOf("1"));

        userMapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 发送激活码
     *
     * @param user
     */
    private void sendActiveCode(UserDO user) {
        //缓存存入激活码
        String id = user.getId().toString();
        String activeCode = MsgDigestUtils.md5(id);
        cacheTemplate4expired.put(activeCodeKeyPrefix + activeCode, id);

        //发送邮件：激活码
        String mailContent = String.format(
                "ICQL API: 请您点击或复制访问链接 <a href='https://api.icql.work/user/active?code=%s' style='color:red'>http://api.icql.work/user/active?code=%s</a> 来激活您的账户。",
                activeCode, activeCode);
        sendMail(user.getEmail(), mailContent);
    }

    /**
     * 发送邮件
     *
     * @param toEmail
     * @param content
     */
    private void sendMail(String toEmail, String content) {
        try {
            MailUtils.sendQQMail(mailFrom, mailPassword, toEmail, "ICQL API", content);
        } catch (MessagingException e) {
            throw new ServiceException("邮件发送失败" + e.getMessage());
        }
    }
}
