package work.icql.account;


import work.icql.account.util.EkpUtils;
import work.icql.account.util.LogUtils;
import work.icql.account.util.SysUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * @author icql
 * @version 1.0
 * @date 2018/11/20 11:48
 * @Title App
 * @Description App
 */
public class App {
    public static void main(String[] args) {
        System.out.println("开始执行中，请在窗口关闭后查看.log日志文件");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String logFilePath = SysUtils.getSysPath() + "/" + simpleDateFormat.format(new Date()) + ".log";

        LogUtils.writeLog(logFilePath, "开始");
        try {
            //region 读取配置
            BufferedReader bufferedReader = new BufferedReader(new FileReader(SysUtils.getSysPath() + "/App.properties"));
            Properties properties = new Properties();
            properties.load(bufferedReader);
            bufferedReader.close();
            String jdbcUrl = properties.getProperty("jdbcUrl");
            String dbUser = properties.getProperty("dbUser");
            String dbPassword = properties.getProperty("dbPassword");

            String opUserCode = properties.getProperty("opUserCode");
            String opPassword = properties.getProperty("opPassword");

            String userCodes = properties.getProperty("addUserCodes");
            String[] arrUserCodes = null;
            if (userCodes.indexOf(",") > 0) {
                arrUserCodes = userCodes.split(",");
            } else if (userCodes.indexOf("，") > 0) {
                arrUserCodes = userCodes.split("，");
            } else if (userCodes.indexOf("/") > 0) {
                arrUserCodes = userCodes.split("/");
            } else {
                arrUserCodes = userCodes.split(",");
            }

            List<String> data = new ArrayList<>();
            for (String usercode : arrUserCodes) {
                if (usercode.trim().length() > 0) {
                    data.add(usercode.trim().replace("a", "").replace("A", ""));
                }
            }
            //endregion

            if (data.size() == 0) {
                return;
            }

            EkpUtils.addEkpUser(jdbcUrl, dbUser, dbPassword, logFilePath, opUserCode, opPassword, data);
        } catch (Exception e) {
            LogUtils.writeLog(logFilePath, e.getMessage());
            e.printStackTrace();
        }
        LogUtils.writeLog(logFilePath, "结束");
    }
}
