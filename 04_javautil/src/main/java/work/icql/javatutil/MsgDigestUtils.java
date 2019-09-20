package work.icql.javatutil;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * @author icql
 * @version 1.0
 * @date 2018/11/17 10:19
 * @Title MsgDigestUtils
 * @Description MsgDigestUtils
 */
public final class MsgDigestUtils {

    private MsgDigestUtils() {
    }

    /**
     * 获取指定长度的随机字符串
     *
     * @param length
     * @return
     */
    public static String getRandomString(int length) {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(3);
            long result = 0;
            switch (number) {
                case 0:
                    result = Math.round(Math.random() * 25 + 65);
                    sb.append(String.valueOf((char) result));
                    break;
                case 1:
                    result = Math.round(Math.random() * 25 + 97);
                    sb.append(String.valueOf((char) result));
                    break;
                case 2:
                    sb.append(String.valueOf(new Random().nextInt(10)));
                    break;
            }
        }
        return sb.toString();
    }

    /**
     * md5加密
     *
     * @param strText
     * @return
     */
    public static String md5(String strText) {
        String result = null;
        if (strText != null && strText.length() > 0) {
            byte[] secretBytes = null;
            try {
                secretBytes = MessageDigest.getInstance("md5").digest(strText.getBytes());
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            result = new BigInteger(1, secretBytes).toString(16);// 16进制数字
            // 如果生成数字未满32位，需要前面补0
            for (int i = 0; i < 32 - result.length(); i++) {
                result = "0" + result;
            }
        }
        return result;
    }

    /**
     * sha256加密
     *
     * @param strText
     * @return
     */
    public static String sha256(String strText) {
        return sha(strText, "SHA-256");
    }

    /**
     * sha512加密
     *
     * @param strText
     * @return
     */
    public static String sha512(String strText) {
        return sha(strText, "SHA-512");
    }


    private static String sha(String strText, String strType) {
        String strResult = null;
        // 是否是有效字符串
        if (strText != null && strText.length() > 0) {
            try {
                // SHA 加密开始
                // 创建加密对象 并傳入加密類型
                MessageDigest messageDigest = MessageDigest.getInstance(strType);
                // 传入要加密的字符串
                messageDigest.update(strText.getBytes());
                // 得到 byte 類型结果
                byte byteBuffer[] = messageDigest.digest();

                // 將 byte 轉換爲 string
                StringBuffer strHexString = new StringBuffer();
                // 遍歷 byte buffer
                for (int i = 0; i < byteBuffer.length; i++) {
                    String hex = Integer.toHexString(0xff & byteBuffer[i]);
                    if (hex.length() == 1) {
                        strHexString.append('0');
                    }
                    strHexString.append(hex);
                }
                // 得到返回結果
                strResult = strHexString.toString();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }

        return strResult;
    }
}
