package work.icql.ssm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import work.icql.javatutil.CacheManager;
import work.icql.javatutil.HttpUtils;
import work.icql.javatutil.MsgDigestUtils;
import work.icql.ssm.mapper.TbSSOUserMapper;
import work.icql.ssm.pojo.IcqlResult;
import work.icql.ssm.pojo.TbSSOUser;
import work.icql.ssm.pojo.TbSSOUserExample;
import work.icql.ssm.service.SSOService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author icql
 * @version 1.0
 * @date 2018/11/17 11:07
 * @Title SSOServiceImpl
 * @Description SSOServiceImpl
 */
@Service
public class SSOServiceImpl implements SSOService {

    @Autowired
    private TbSSOUserMapper ssoUserMapper;

    @Autowired
    private CacheManager cacheManager;

    @Override
    public String login(HttpServletRequest request, Model model, String service) {
        String TGC = HttpUtils.getCookieValue(request, "ICQLTGC");
        if (!StringUtils.isEmpty(TGC)) {
            String ST = getST(request, service);

            //sso单机服务器：使用自定义缓存管理器
            Object user = cacheManager.get(TGC);
            if (user == null) {
                model.addAttribute("service", service);
                return "/sso/login";
            }
            cacheManager.set(ST, user, 1000 * 60 * 5);

            //sso集群服务器：使用redis

            return "redirect:" + (StringUtils.isEmpty(service) ? "/sso/success" : service) + "?ticket=" + ST;
        }
        model.addAttribute("service", service);
        return "/sso/login";
    }

    @Override
    public IcqlResult login4form(HttpServletRequest request, HttpServletResponse response, String service, String username, String password) {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return IcqlResult.build(204, "用户名或密码不能为空");
        }
        TbSSOUserExample example = new TbSSOUserExample();
        TbSSOUserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        criteria.andPasswordEqualTo(MsgDigestUtils.md5(password));

        List<TbSSOUser> tbSSOUsers = ssoUserMapper.selectByExample(example);
        if (tbSSOUsers == null || tbSSOUsers.size() == 0) {
            return IcqlResult.build(204, "用户名或密码错误");
        }
        TbSSOUser user = tbSSOUsers.get(0);

        String ST = getST(request, service);
        String TGC = getTGC(request);

        //缓存[key:唯一令牌ST(ticket),用户信息],设置默认生效时间5分钟
        //缓存[key:TGC,用户信息],保存登录用户的信息,设置默认生效时间60分钟

        //使用自定义缓存管理器
        cacheManager.set(ST, user, 1000 * 60 * 5);
        cacheManager.set(TGC, user, 1000 * 60 * 60);

        HttpUtils.setCookie(request, response, "ICQLTGC", TGC);
        return IcqlResult.ok(ST);
    }

    @Override
    public IcqlResult validate(String ticket) {
        if (StringUtils.isEmpty(ticket)) {
            return IcqlResult.build(204, "sso服务器未登录");
        }

        //sso单机服务器：使用自定义缓存管理器
        Object user = cacheManager.get(ticket);
        cacheManager.remove(ticket);

        //sso集群服务器：使用redis

        if (user == null) {
            return IcqlResult.build(204, "sso服务器未登录");
        }

        return IcqlResult.ok(user);
    }

    @Override
    public String logout(HttpServletRequest request, HttpServletResponse response, String service) {
        String TGC = HttpUtils.getCookieValue(request, "ICQLTGC");
        if (!StringUtils.isEmpty(TGC)) {
            //sso单机服务器：使用自定义缓存管理器
            cacheManager.remove(TGC);

            //sso集群服务器：使用redis

            HttpUtils.deleteCookie(request, response, "ICQLTGC");
        }
        return StringUtils.isEmpty(service) ? "/sso/logout" : "redirect:" + service;
    }

    @Override
    public IcqlResult register4check(int type, String value) {
        TbSSOUserExample example = new TbSSOUserExample();
        TbSSOUserExample.Criteria criteria = example.createCriteria();
        switch (type) {
            case 0:
                //密码校验：规则是否符合
                return IcqlResult.ok();
            case 1:
                criteria.andUsernameEqualTo(value);
                break;
            case 2:
                //其他字段校验：如手机号需唯一，邮箱需唯一
                break;
        }

        List<TbSSOUser> tbSSOUsers = ssoUserMapper.selectByExample(example);
        if (tbSSOUsers != null && tbSSOUsers.size() > 0) {
            return IcqlResult.build(204, "用户名已存在");
        }
        return IcqlResult.ok();
    }

    @Override
    public IcqlResult register4form(TbSSOUser user) {
        IcqlResult icqlResult = null;
        //校验数据
        icqlResult = register4check(0, user.getPassword());
        if (icqlResult.getStatus() != 200) {
            return icqlResult;
        }

        //...

        int insert = ssoUserMapper.insert(user);
        if (insert != 1) {
            return IcqlResult.build(204, "注册失败");
        }
        return IcqlResult.ok();
    }


    private String getST(HttpServletRequest request, String service) {
        String clientIP = HttpUtils.getClientIP(request);
        return "ST-" + MsgDigestUtils.getRandomString(6) + "-" + MsgDigestUtils.md5(clientIP + MsgDigestUtils.getRandomString(6) + service) + "-ICQL";
    }

    private String getTGC(HttpServletRequest request) {
        String domainName = HttpUtils.getDomainName(request);
        return "TGC-" + MsgDigestUtils.getRandomString(6) + "-" + MsgDigestUtils.md5(domainName + MsgDigestUtils.getRandomString(6)) + "-ICQL";
    }
}
