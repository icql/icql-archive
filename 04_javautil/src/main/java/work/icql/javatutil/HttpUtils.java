package work.icql.javatutil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @author icql
 * @version 1.0
 * @date 2018/11/17 11:02
 * @Title HttpUtils
 * @Description HttpUtils
 */
public final class HttpUtils {

    private HttpUtils() {
    }

    /**
     * 下载文件
     *
     * @param response
     * @param downloadFileName
     * @param bytes
     */
    public static void download(HttpServletResponse response, String downloadFileName, byte[] bytes) {
        try {
            downloadFileName = URLEncoder.encode(downloadFileName, "UTF-8");
            response.reset();
            response.setHeader("Content-Disposition", "attachment; filename=\"" + downloadFileName + "\"");
            response.addHeader("Content-Length", "" + bytes.length);
            response.setContentType("text/html;charset=UTF-8");
            OutputStream out = new BufferedOutputStream(response.getOutputStream());
            out.write(bytes);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取客户端真实ip
     *
     * @param request
     * @return
     */
    public static String getClientIP(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip != null && ip.length() > 0 && !"unKnown".equalsIgnoreCase(ip)) {
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if (ip != null && ip.length() > 0 && !"unKnown".equalsIgnoreCase(ip)) {
            return ip;
        }
        return request.getRemoteAddr();
    }

    /**
     * 根据Cookie key 获取 value
     *
     * @param request
     * @param cookieKey
     * @return
     */
    public static String getCookieValue(HttpServletRequest request, String cookieKey) {
        return getCookieValue(request, cookieKey, false);
    }

    /**
     * 根据Cookie key 获取 value（是否使用utf-8解码）
     *
     * @param request
     * @param cookieKey
     * @return
     */
    public static String getCookieValue(HttpServletRequest request, String cookieKey, boolean isDecoder) {
        if (isDecoder) {
            return getCookieValue(request, cookieKey, "UTF-8");
        } else {
            return getCookieValue(request, cookieKey, null);
        }
    }


    /**
     * 根据Cookie key 获取 value（使用指定 编码方式 解码）
     *
     * @param request
     * @param cookieKey
     * @param encodeString
     * @return
     */
    public static String getCookieValue(HttpServletRequest request, String cookieKey, String encodeString) {
        Cookie[] cookieList = request.getCookies();
        if (cookieList == null || cookieKey == null) {
            return null;
        }
        String retValue = null;
        try {
            for (int i = 0; i < cookieList.length; i++) {
                if (cookieList[i].getName().equals(cookieKey)) {
                    if (encodeString == null || encodeString.length() == 0) {
                        retValue = cookieList[i].getValue();
                    } else {
                        retValue = URLDecoder.decode(cookieList[i].getValue(), encodeString);
                    }
                    break;
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return retValue;
    }


    /***********************************************************************************************************/


    /**
     * 设置 Cookie 的值（默认会话级cookie，关闭浏览器失效，不编码）
     *
     * @param request
     * @param response
     * @param cookieKey
     * @param cookieValue
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieKey,
                                 String cookieValue) {
        setCookie(request, response, cookieKey, cookieValue, -1);
    }

    /**
     * 设置 Cookie 的值（默认会话级cookie，关闭浏览器失效，不编码）
     *
     * @param request
     * @param response
     * @param cookieKey
     * @param cookieValue
     * @param domain      作用的域名
     * @param path        作用的路径
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieKey,
                                 String cookieValue, String domain, String path) {
        setCookie(request, response, cookieKey, cookieValue, -1, domain, path);
    }

    /**
     * 设置 Cookie 的值 （默认会话级cookie，关闭浏览器失效，是否使用 utf-8 编码）
     *
     * @param request
     * @param response
     * @param cookieKey
     * @param cookieValue
     * @param isEncode
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieKey,
                                 String cookieValue, boolean isEncode) {
        setCookie(request, response, cookieKey, cookieValue, -1, isEncode);
    }

    /**
     * 设置 Cookie 的值 （默认会话级cookie，关闭浏览器失效，是否使用 utf-8 编码）
     *
     * @param request
     * @param response
     * @param cookieKey
     * @param cookieValue
     * @param isEncode
     * @param domain      作用的域名
     * @param path        作用的路径
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieKey,
                                 String cookieValue, boolean isEncode, String domain, String path) {
        setCookie(request, response, cookieKey, cookieValue, -1, isEncode, domain, path);
    }

    /**
     * 设置 Cookie 的值 （默认不编码）
     *
     * @param request
     * @param response
     * @param cookieKey
     * @param cookieValue
     * @param MaxAge
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieKey,
                                 String cookieValue, int MaxAge) {
        setCookie(request, response, cookieKey, cookieValue, MaxAge, false);
    }

    /**
     * 设置 Cookie 的值 （默认不编码）
     *
     * @param request
     * @param response
     * @param cookieKey
     * @param cookieValue
     * @param MaxAge
     * @param domain      作用的域名
     * @param path        作用的路径
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieKey,
                                 String cookieValue, int MaxAge, String domain, String path) {
        setCookie(request, response, cookieKey, cookieValue, MaxAge, false, domain, path);
    }

    /**
     * 设置 Cookie 的值 （是否使用 utf-8 编码）
     *
     * @param request
     * @param response
     * @param cookieKey
     * @param cookieValue
     * @param MaxAge
     * @param isEncode    是否使用 utf-8 编码
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieKey,
                                 String cookieValue, int MaxAge, boolean isEncode) {
        if (isEncode) {
            setCookie(request, response, cookieKey, cookieValue, MaxAge, "utf-8");
        } else {
            setCookie(request, response, cookieKey, cookieValue, MaxAge, null);
        }
    }

    /**
     * 设置 Cookie 的值 （是否使用 utf-8 编码）
     *
     * @param request
     * @param response
     * @param cookieKey
     * @param cookieValue
     * @param MaxAge
     * @param isEncode    是否使用 utf-8 编码
     * @param domain      作用的域名
     * @param path        作用的路径
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieKey,
                                 String cookieValue, int MaxAge, boolean isEncode, String domain, String path) {
        if (isEncode) {
            setCookie(request, response, cookieKey, cookieValue, MaxAge, "utf-8", domain, path);
        } else {
            setCookie(request, response, cookieKey, cookieValue, MaxAge, null, domain, path);
        }
    }

    /**
     * 设置 Cookie 的值
     *
     * @param request
     * @param response
     * @param cookieKey    key
     * @param cookieValue  value
     * @param MaxAge       最大有效时间 [-1:默认值,会话级cookie，关闭浏览器失效，
     *                     0:不记录cookie，
     *                     其他:单位秒]
     * @param encodeString 编码字符串
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieKey,
                                 String cookieValue, int MaxAge, String encodeString) {
        setCookie(request, response, cookieKey, cookieValue, MaxAge, encodeString, null, null);
    }


    /**
     * 设置 Cookie 的值
     *
     * @param request
     * @param response
     * @param cookieKey    key
     * @param cookieValue  value
     * @param MaxAge       最大有效时间 [-1:默认值,会话级cookie，关闭浏览器失效，
     *                     0:不记录cookie，
     *                     其他:单位秒]
     * @param encodeString 编码字符串
     * @param domain       作用的域名
     * @param path         作用的路径
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieKey,
                                 String cookieValue, int MaxAge, String encodeString, String domain, String path) {
        try {
            if (cookieValue == null) {
                cookieValue = "";
            } else {
                if (encodeString != null && encodeString.length() > 0) {
                    cookieValue = URLEncoder.encode(cookieValue, encodeString);
                }
            }
            Cookie cookie = new Cookie(cookieKey, cookieValue);
            cookie.setMaxAge(MaxAge);

            if (domain != null && domain.length() > 0) {
                cookie.setDomain(domain);
            }
            if (path != null && path.length() > 0) {
                cookie.setPath(path);
            } else {
                cookie.setPath("/");
            }

            response.addCookie(cookie);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除 Cookie
     *
     * @param request
     * @param response
     * @param cookieKey
     */
    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response,
                                    String cookieKey) {
        setCookie(request, response, cookieKey, "", 0, false);
    }

    /**
     * 获取通用域名（用此域名设置的cookie，一级域名和二级域名都可以使用）
     *
     * @param request
     * @return
     */
    public static String getDomainName(HttpServletRequest request) {
        String domainName = null;

        String serverName = request.getRequestURL().toString();
        if (serverName == null || serverName.equals("")) {
            domainName = "";
        } else {
            serverName = serverName.toLowerCase();
            serverName = serverName.substring(7);
            final int end = serverName.indexOf("/");
            serverName = serverName.substring(0, end);
            final String[] domains = serverName.split("\\.");
            int len = domains.length;
            if (len > 3) {
                // www.xxx.com.cn
                domainName = "." + domains[len - 3] + "." + domains[len - 2] + "." + domains[len - 1];
            } else if (len <= 3 && len > 1) {
                // xxx.com or xxx.cn
                domainName = "." + domains[len - 2] + "." + domains[len - 1];
            } else {
                domainName = serverName;
            }
        }

        if (domainName != null && domainName.indexOf(":") > 0) {
            String[] ary = domainName.split("\\:");
            domainName = ary[0];
        }
        return domainName;
    }
}
