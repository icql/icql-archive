package work.icql.javatutil;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author icql
 * @version 1.0
 * @date 2018/11/20 14:58
 * @Title SysUtils
 * @Description SysUtils
 */
public final class SysUtils {

    private SysUtils() {
    }

    /**
     * 获取当前.class路径或者.jar路径
     *
     * @return
     */
    public static String getSysPath() {
        String path = SysUtils.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        if (path.toUpperCase().indexOf(".JAR") != -1) {
            try {
                String StrPath = path.substring(0, path.toUpperCase().indexOf(".jar".toUpperCase()));
                //System.out.println(StrPath);
                path = StrPath.substring(0, StrPath.lastIndexOf("/") + 1);
            } catch (Exception e) {
                return "出错了:" + e.toString();
            }
        }
        return path;
    }

    /**
     * 获取Throwable详细信息
     *
     * @param t
     * @return
     */
    public static String getStackTrace(Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        try {
            t.printStackTrace(pw);
            return sw.toString();
        } finally {
            pw.close();
        }
    }
}
