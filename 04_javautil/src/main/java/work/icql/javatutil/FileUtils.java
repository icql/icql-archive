package work.icql.javatutil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author icql
 * @version 1.0
 * @date 2018/10/24 16:41
 * @Title FileUtils
 * @Description 文件工具类
 */
public final class FileUtils {

    private FileUtils() {
    }

    /**
     * 根据字节数组获得文件类型
     *
     * @param bytes
     * @return
     */
    public static String getFileType(byte[] bytes) {
        String result = "";

        String value = bytes2HexString(bytes);

        System.out.println(value);

        Map<String, String> allFileType = getAllFileType();
        for (Map.Entry<String, String> entry : allFileType.entrySet()) {
            if (value.startsWith(entry.getKey())) {
                result = entry.getValue();
                break;
            }
        }
        if (null == result || "".equals(result)) {
            result = "未识别到文件类型";
        }

        return result;
    }

    /**
     * 根据文件路径获得文件类型
     *
     * @param filePath
     * @return
     * @throws IOException
     */
    public static String getFileType(String filePath) {
        String result = "";
        InputStream in = null;
        try {
            in = new FileInputStream(filePath);
            byte[] bytes = new byte[20];
            in.read(bytes, 0, bytes.length);

            result = getFileType(bytes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }


    /**
     * 字节数组=>十六进制字符串
     *
     * @param bytes 字节数组
     * @return 十六进制字符串
     */
    private static String bytes2HexString(byte[] bytes) {
        if (null == bytes) {
            throw new NullPointerException();
        }

        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", new Integer(b & 0xff)));
        }

        return sb.toString();
    }

    /**
     * 获得文件类型Map
     *
     * @return
     */
    private static Map<String, String> getAllFileType() {
        Map<String, String> FILE_TYPE_MAP = new HashMap<>();

        FILE_TYPE_MAP.put("ffd8ff", "jpg");
        FILE_TYPE_MAP.put("89504e47", "png");
        FILE_TYPE_MAP.put("47494638", "gif");
        FILE_TYPE_MAP.put("424d", "bmp");

        return FILE_TYPE_MAP;
    }
}
