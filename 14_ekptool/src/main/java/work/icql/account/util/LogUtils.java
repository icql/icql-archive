package work.icql.account.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author icql
 * @version 1.0
 * @date 2018/11/15 16:56
 * @Title LogUtils
 * @Description LogUtils
 */
public class LogUtils {

    /**
     * 写入日志
     * @param logFilePath
     * @param content
     */
    public static void writeLog(String logFilePath, String content) {
        FileWriter fw = null;
        try {
            File file = new File(logFilePath);
            fw = new FileWriter(file, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter pw = new PrintWriter(fw);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        pw.println(sdf.format(new Date()) + "---" + content);
        pw.flush();
        try {
            fw.flush();
            pw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
