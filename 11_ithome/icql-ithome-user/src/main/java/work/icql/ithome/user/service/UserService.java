package work.icql.ithome.user.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import work.icql.ithome.common.util.IdWorker;
import work.icql.ithome.user.entity.User;
import work.icql.ithome.user.repository.UserRepository;

import javax.persistence.criteria.Predicate;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author icql
 * @version 1.0
 * @date 2019/1/29 10:46
 * @Title UserService
 * @Description UserService
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(String id) {
        return userRepository.findById(id).get();
    }

    public void add(User user) {
        user.setId(idWorker.nextId() + "");//设置ID

        //密码加密
        String newpassword = encoder.encode(user.getPassword());//加密后的密码
        user.setPassword(newpassword);

        userRepository.save(user);
    }

    public void update(User user) {
        userRepository.save(user);
    }

    public void deleteById(String id) {
        userRepository.deleteById(id);
    }

    public List<User> findSearch(User user) {
        Specification specification = createSpecification(user);
        return userRepository.findAll(specification);
    }

    /**
     * 分页多条件查询
     *
     * @param user
     * @param page
     * @param size
     * @return
     */
    public Page<User> findSearch(User user, int page, int size) {
        Specification specification = createSpecification(user);
        return userRepository.findAll(specification, PageRequest.of(page - 1, size));
    }

    /**
     * 构造查询条件
     *
     * @param user
     * @return
     */
    private Specification<User> createSpecification(User user) {
        return (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (!StringUtils.isEmpty(user.getNickname())) {
                predicateList.add(cb.like(root.get("nickname").as(String.class), "%" + user.getNickname() + "%"));
            }

            query.where(predicateList.toArray(new Predicate[predicateList.size()]));
            //query.orderBy(cb.desc(root.get("username")),cb.asc(root.get("state")));
            return query.getRestriction();
        };
    }

    /**
     * 发送手机验证码
     *
     * @param mobile
     */
    public void sendSms(String mobile) {
        //1.生成6位短信验证码
        Random random = new Random();
        int max = 999999;//最大数
        int min = 100000;//最小数
        int code = random.nextInt(max);//随机生成
        if (code < min) {
            code = code + min;
        }
        System.out.println(mobile + "收到验证码是：" + code);
        //2.将验证码放入redis
        redisTemplate.opsForValue().set("smscode_" + mobile, code + "", 5, TimeUnit.MINUTES);//五分钟过期
        // 3.将验证码和手机号发动到rabbitMQ中
        Map<String, String> map = new HashMap();
        map.put("mobile", mobile);
        map.put("code", code + "");
        rabbitTemplate.convertAndSend("", "sms", map);
    }

    /**
     * 注册用户
     *
     * @param user
     * @param code
     */
    public void register(User user, String code) {
        //判断验证码是否正确
        String syscode = (String) redisTemplate.opsForValue().get("smscode_" + user.getMobile());

        //提取系统正确的验证码
        if (syscode == null) {
            throw new RuntimeException("请点击获取短信验证码");
        }
        if (!syscode.equals(code)) {
            throw new RuntimeException("验证码输入不正确");
        }
        user.setId(idWorker.nextId() + "");
        user.setFollowcount(0L);//关注数
        user.setFanscount(0L);//粉丝数
        user.setOnline(0L);//在线时长
        user.setRegdate(new Date());//注册日期
        user.setUpdate(new Date());//更新日期
        user.setLastdate(new Date());//最后登陆日期
        userRepository.save(user);
    }

    public User findByMobileAndPassword(String mobile, String password) {
        User user = userRepository.findByMobile(mobile);
        if (user != null && encoder.matches(password, user.getPassword())) {
            return user;
        } else {
            return null;
        }
    }
}
