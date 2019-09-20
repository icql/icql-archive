package work.icql.javatutil;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

/**
 * @author icql
 * @version 1.0
 * @date 2018/10/31 10:07
 * @Title JacksonUtils
 * @Description JacksonUtils
 */
public final class JacksonUtils {

    private JacksonUtils() {
    }

    //定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static String object2json(Object object) {
        try {
            return MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T json2object(String json, Class<T> object) {
        try {
            return MAPPER.readValue(json, object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> List<T> json2list(String json, Class<T> object) {
        JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, object);
        try {
            return MAPPER.readValue(json, javaType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
