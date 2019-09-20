package work.icql.javatutil;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author icql
 * @version 1.0
 * @date 2018/10/31 10:25
 * @Title FastjsonUtils
 * @Description FastjsonUtils
 */
@SuppressWarnings("unchecked")
public final class FastjsonUtils {
    private FastjsonUtils() {
    }

    /**
     * 对象转换成json字符串
     *
     * @param obj
     * @return
     */
    public static String object2json(Object obj) {
        return JSON.toJSONString(obj);
    }

    /**
     * List集合转换成json字符串
     *
     * @param obj
     * @return
     */
    public static String list2json(Object obj) {
        return JSONArray.toJSONString(obj, true);
    }

    /**
     * map转json字符串
     *
     * @param map
     * @return
     */
    public static String map2json(Map map) {
        String jsonStr = JSON.toJSONString(map);
        return jsonStr;
    }

    /**
     * json字符串转换成对象
     *
     * @param jsonString
     * @param cls
     * @return
     */
    public static <T> T json2object(String jsonString, Class<T> cls) {
        T t = null;
        try {
            t = JSON.parseObject(jsonString, cls);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * json字符串转换成List集合
     * (需要实体类)
     *
     * @param jsonString
     * @return
     */
    public static List<Object> json2list(String jsonString, Class cls) {
        List<Object> list = null;
        try {
            list = JSON.parseArray(jsonString, cls);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * json字符串转map集合
     *
     * @param json
     * @return
     */
    public static HashMap json2map(String json) {
        return JSON.parseObject(json, HashMap.class);
    }
}
