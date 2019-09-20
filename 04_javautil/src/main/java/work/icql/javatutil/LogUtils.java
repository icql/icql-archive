package work.icql.javatutil;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
public final class LogUtils {
    private LogUtils() {
    }

    /**
     * 写入日志
     * @param logFilePath
     * @param content
     */
    public static void writeLog(String logFilePath, String content) {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileOutputStream(logFilePath,true));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            pw.println(sdf.format(new Date()) + "---" + content);
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            if(pw != null){
                pw.close();
            }
        }

    }
}
