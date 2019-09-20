package work.icql.javatutil;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author icql
 * @version 1.0
 * @date 2018/11/8 10:54
 * @Title ReflectionUtils
 * @Description ReflectionUtils
 */
public final class ReflectionUtils {

    private ReflectionUtils() {
    }

    /**
     * 通过反射类取所有字段名
     *
     * @param clazz 反射类
     * @return
     */
    public static List<String> getAllFieldName(Class<?> clazz) {
        List<String> fieldNames = new ArrayList<>();
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            fieldNames.add(field.getName());
        }
        return fieldNames;
    }

    /**
     * 通过反射取对象指定字段(属性)的值
     *
     * @param target    目标对象
     * @param fieldName 字段名
     * @return 字段值
     */
    public static Object getFieldValue(Object target, String fieldName) {
        Object re = null;
        Class<?> clazz = target.getClass();
        String[] fs = fieldName.split("\\.");
        try {
            for (int i = 0; i < fs.length - 1; i++) {
                Field f = clazz.getDeclaredField(fs[i]);
                f.setAccessible(true);
                target = f.get(target);
                clazz = target.getClass();
            }

            Field f = clazz.getDeclaredField(fs[fs.length - 1]);
            f.setAccessible(true);
            re = f.get(target);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return re;
    }

    /**
     * 通过反射给对象的指定字段赋值
     *
     * @param target     目标对象
     * @param fieldName  字段名
     * @param fieldValue 字段值
     */
    public static void setFieldValue(Object target, String fieldName, Object fieldValue) {
        Class<?> clazz = target.getClass();
        String[] fs = fieldName.split("\\.");
        try {
            for (int i = 0; i < fs.length - 1; i++) {
                Field f = clazz.getDeclaredField(fs[i]);
                f.setAccessible(true);
                Object val = f.get(target);
                if (val == null) {
                    Constructor<?> c = f.getType().getDeclaredConstructor();
                    c.setAccessible(true);
                    val = c.newInstance();
                    f.set(target, val);
                }
                target = val;
                clazz = target.getClass();
            }

            Field f = clazz.getDeclaredField(fs[fs.length - 1]);
            f.setAccessible(true);

            //为PoiUtil使用 20181108
            if (null != fieldValue && fieldValue instanceof String) {
                //待添加更多类型
                String type = f.getGenericType().toString().substring(6);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                switch (type) {
                    case "java.lang.String":
                        f.set(target, fieldValue.toString());
                        break;
                    case "java.lang.Double":
                        f.set(target, Double.valueOf(fieldValue.toString()));
                        break;
                    case "java.lang.Integer":
                        f.set(target, Integer.valueOf(fieldValue.toString()));
                        break;
                    case "java.util.Date":
                        String value = fieldValue.toString();
                        value.replace("/", "-").replace("年", "").replace("月", "").replace("日", "");
                        f.set(target, sdf.parse(value));
                        break;
                    default:
                        f.set(target, fieldValue);
                        break;
                }
            } else {
                f.set(target, fieldValue);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
