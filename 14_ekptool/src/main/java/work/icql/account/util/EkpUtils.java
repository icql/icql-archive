package work.icql.account.util;

import org.apache.http.client.CookieStore;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import work.icql.account.pojo.EkpConst;
import work.icql.account.pojo.EkpSysOrgElement;
import work.icql.account.pojo.JdbcDbtype;
import work.icql.account.pojo.JdbcSetting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author icql
 * @version 1.0
 * @date 2018/11/15 9:19
 * @Title EkpUtils
 * @Description EkpUtils
 */
public class EkpUtils {

    /**
     * sso服务器登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 返回 null 说明登陆失败
     */
    public static CookieStore ssoLogin(String username, String password) {
        CookieStore cookieStore = new BasicCookieStore();
        HttpClient.doGet(EkpConst.SSOURL.getValue(), null, cookieStore);
        Cookie cookie = cookieStore.getCookies().get(0);
        Map<String, String> map = new HashMap<>();
        map.put("username", username.toLowerCase());
        map.put("password", password);
        map.put("lt", "e1s1");
        map.put("_eventId", "submit");

        cookieStore = new BasicCookieStore();
        cookieStore.addCookie(cookie);
        String ssologinUrl = EkpConst.SSOLOGINURL.getValue() + ";" + cookie.getName() + "=" + cookie.getValue();
        String ssologinResult = HttpClient.doPost(ssologinUrl, map, cookieStore);
        String loginHtmlText = "请输入用户名和密码";
        if (ssologinResult == null || "".equals(ssologinResult) || ssologinResult.indexOf(loginHtmlText) > 0) {
            return null;
        }
        return cookieStore;
    }

    /**
     * 通过 url 执行ekp操作
     *
     * @param cookieStore sso登录成功返回的cookie
     * @param url         操作url
     * @return response结果
     */
    public static String doByUrl(CookieStore cookieStore, String url) {
        return HttpClient.doGet(url, null, cookieStore);
    }


    /**
     * 增加Ekp用户，设置初始密码（xmjlA+数字工号），修改邮件信息
     *
     * @param jdbcUrl
     * @param dbUser
     * @param dbPassword
     * @param logFilePath
     * @param opUserCode
     * @param opPassword
     * @param userCodes
     * @return
     * @throws Exception
     */
    public static int addEkpUser(String jdbcUrl, String dbUser, String dbPassword, String logFilePath, String opUserCode, String opPassword, List<String> userCodes) throws Exception {
        //参数校验
        if (userCodes == null || userCodes.size() == 0) {
            return 0;
        }

        //sso登录
        LogUtils.writeLog(logFilePath, "sso登录");
        CookieStore cookieStore = ssoLogin(opUserCode, opPassword);
        if (cookieStore == null) {
            throw new Exception("sso登录失败，请检查用户名密码");
        }

        //调用EKP接口新增用户
        StringBuilder sbUserCodes = new StringBuilder();
        for (String userCode : userCodes) {
            String html = doByUrl(cookieStore, EkpConst.ADDUSERURL.getValue() + userCode);
            if (html == null || "".equals(html)) {
                LogUtils.writeLog(logFilePath, "新增用户：" + userCode + " 失败");
            } else {
                LogUtils.writeLog(logFilePath, "新增用户：" + userCode + " 成功");
                sbUserCodes.append("'");
                sbUserCodes.append(userCode);
                sbUserCodes.append("',");
            }
        }
        String strUserCodes = sbUserCodes.substring(0, sbUserCodes.length() - 1);

        //oss接入
        doByUrl(cookieStore, EkpConst.OSSINURL.getValue());
        LogUtils.writeLog(logFilePath, "oss接入");

        //休眠5秒，等待OSS接入成功
        TimeUnit.SECONDS.sleep(5);


        //数据库update个人信息
        String sqlGetUsers = String.format("select e.* from ekp.sys_org_element e  where e.fd_no in (%s)", strUserCodes);
        String sqlUpdateUser = "update ekp.sys_org_person set fd_email = ?,fd_inotes_email= ?,fd_password= ? where fd_id= ?";
        String sqlCheckEmail = "select * from ekp.sys_org_element e  where e.fd_name_pinyin = ";
        String sqlUpdateUserName = "update ekp.sys_org_element set fd_name = ?,fd_is_fc = 1 where fd_id= ?";

        int result = 0;

        JdbcSetting jdbcUtilSetting = JdbcUtils.getJdbcSetting(JdbcDbtype.ORACLE, jdbcUrl, dbUser, dbPassword);
        List<EkpSysOrgElement> users = JdbcUtils.executeQuery(jdbcUtilSetting, EkpSysOrgElement.class, sqlGetUsers, null);

        for (EkpSysOrgElement user : users) {

            //检查邮箱名是否重复
            List<EkpSysOrgElement> checkUsers = JdbcUtils.executeQuery(jdbcUtilSetting, EkpSysOrgElement.class, sqlCheckEmail + "'" + user.getFD_NAME_PINYIN() + "'", null);
            if (checkUsers.size() > 1) {
                String newUserName = user.getFD_NAME().trim() + user.getFD_NO();
                String newUserNamePinyin = user.getFD_NAME_PINYIN().trim() + user.getFD_NO();
                user.setFD_NAME(newUserName);
                user.setFD_NAME_PINYIN(newUserNamePinyin);
            }

            //更新 姓名/是否在所有组织架构中显示
            List<Object> params4UserName = new ArrayList<>();
            params4UserName.add(user.getFD_NAME());
            params4UserName.add(user.getFD_ID());
            JdbcUtils.executeUpdate(jdbcUtilSetting, sqlUpdateUserName, params4UserName);

            //更新 邮箱/邮箱名/密码
            List<Object> params = new ArrayList<>();
            params.add(user.getFD_NAME_PINYIN().trim() + "@xmjl.com");
            params.add(user.getFD_NAME_PINYIN().trim());
            params.add(MsgDigestUtils.md5("xmjlA" + user.getFD_NO()));
            params.add(user.getFD_ID());

            int update = JdbcUtils.executeUpdate(jdbcUtilSetting, sqlUpdateUser, params);
            LogUtils.writeLog(logFilePath, "信息修改：" + user.getFD_NO() + " " + user.getFD_NAME() + " mail\\" + user.getFD_NAME_PINYIN().trim() + ".nsf");
            result += update;
        }

        //oss接出
        doByUrl(cookieStore, EkpConst.OSSOUTURL.getValue());
        LogUtils.writeLog(logFilePath, "oss接出");

        return result;
    }
}
