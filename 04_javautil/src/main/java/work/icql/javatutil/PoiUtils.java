package work.icql.javatutil;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.*;

/**
 * @author icql
 * @version 1.0
 * @date 2018/11/8 9:29
 * @Title PoiUtils
 * @Description PoiUtils
 */
public final class PoiUtils {

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface FieldProp {
        String name() default "";

        int colIndex() default -1;
    }

    private PoiUtils() {
    }

    /**
     * excel2list [pojo类:字段需要注解FieldProp]
     *
     * @param bytes excel数据[xls文件]
     * @param clazz pojo类
     * @param <T>
     * @return
     */
    public static <T> List<T> excel2list(Class<T> clazz, byte[] bytes) {
        return excel2list(clazz, bytes, 0);
    }

    /**
     * excel2list [pojo类:字段需要注解FieldProp]
     *
     * @param bytes     excel数据[xls文件]
     * @param clazz     pojo类
     * @param excelType excel类型: [0:xls,1:xlsx]
     * @param <T>
     * @return
     */
    public static <T> List<T> excel2list(Class<T> clazz, byte[] bytes, int excelType) {
        return excel2list(clazz, bytes, excelType, 0, 0);
    }

    /**
     * excel2list [pojo类:字段需要注解FieldProp]
     *
     * @param bytes          excel数据[xls文件]
     * @param clazz          pojo类
     * @param excelType      excel类型: [0:xls,1:xlsx]
     * @param sheetIndex     sheet index
     * @param headerRowIndex 列名标题栏 index
     * @param <T>
     * @return
     */
    public static <T> List<T> excel2list(Class<T> clazz, byte[] bytes,
                                         int excelType, int sheetIndex, int headerRowIndex) {
        return excel2list(clazz, bytes, getMapHeaderFieldByFieldProp(clazz), excelType, 0, 0);
    }

    /**
     * excel2list
     *
     * @param bytes          excel数据[xls文件]
     * @param clazz          pojo类
     * @param mapHeaderField map[key:列名,value:pojo字段名]
     * @param <T>
     * @return
     */
    public static <T> List<T> excel2list(Class<T> clazz, byte[] bytes, Map<String, String> mapHeaderField) {
        return excel2list(clazz, bytes, mapHeaderField, 0, 0, 0);
    }

    /**
     * excel2list
     *
     * @param bytes          excel数据
     * @param clazz          pojo类
     * @param mapHeaderField map[key:列名,value:pojo字段名]
     * @param excelType      excel类型: [0:xls,1:xlsx]
     * @param <T>
     * @return
     */
    public static <T> List<T> excel2list(Class<T> clazz, byte[] bytes, Map<String, String> mapHeaderField,
                                         int excelType) {
        return excel2list(clazz, bytes, mapHeaderField, excelType, 0, 0);
    }

    /**
     * excel2list
     *
     * @param bytes          excel数据
     * @param clazz          pojo类
     * @param mapHeaderField map[key:列名,value:pojo字段名]
     * @param excelType      excel类型: [0:xls,1:xlsx]
     * @param sheetIndex     sheet index
     * @param headerRowIndex 列名标题栏 index
     * @param <T>
     * @return
     */
    public static <T> List<T> excel2list(Class<T> clazz, byte[] bytes, Map<String, String> mapHeaderField,
                                         int excelType, int sheetIndex, int headerRowIndex) {
        Workbook workbook = null;
        List<T> list = null;
        InputStream in = new ByteArrayInputStream(bytes);
        try {
            if (0 == excelType) {
                workbook = new HSSFWorkbook(in);
            } else {
                workbook = new XSSFWorkbook(in);
            }
            in.close();
            Sheet sheet = workbook.getSheetAt(sheetIndex);
            int colNum = sheet.getRow(headerRowIndex).getLastCellNum();//总列数（包括null列）
            int rowNum = sheet.getLastRowNum();//总行数
            Map<Integer, String> mapIndexHeader = new HashMap<>();

            Row headerRow = sheet.getRow(headerRowIndex);
            for (int i = 0; i < colNum; i++) {
                Cell cell = headerRow.getCell(i);
                if (null != cell) {
                    mapIndexHeader.put(i, cell.getStringCellValue());
                }
            }
            list = new ArrayList<>();
            for (int i = headerRowIndex; i < rowNum; i++) {
                Row row = sheet.getRow(i + 1);
                T t = clazz.newInstance();
                for (int j = 0; j < colNum; j++) {
                    String header = mapIndexHeader.get(j);
                    if (null != header) {
                        String fieldName = mapHeaderField.get(header);
                        Cell cell = row.getCell(j);
                        cell.setCellType(CellType.STRING);
                        ReflectionUtils.setFieldValue(t, fieldName, cell.getStringCellValue());
                    }
                }
                list.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * =================================================================================================================
     */


    /**
     * list2excel [pojo类:字段需要注解FieldProp]
     *
     * @param clazz pojo类
     * @param list  list数据
     * @param <T>
     * @return
     */
    public static <T> byte[] list2excel(Class<T> clazz, List<T> list) {
        return list2excel(clazz, list, getMapHeaderFieldByFieldProp(clazz));
    }

    /**
     * list2excel
     *
     * @param clazz          pojo类
     * @param list           list数据
     * @param mapHeaderField map[key:列名,value:pojo字段名]
     * @param <T>
     * @return
     */
    public static <T> byte[] list2excel(Class<T> clazz, List<T> list, LinkedHashMap<String, String> mapHeaderField) {
        return list2excel(clazz, list, mapHeaderField, 0);
    }

    /**
     * list2excel
     *
     * @param clazz          pojo类
     * @param list           list数据
     * @param mapHeaderField map[key:列名,value:pojo字段名]
     * @param excelType      excel类型: [0:xls,1:xlsx]
     * @param <T>
     * @return
     */
    public static <T> byte[] list2excel(Class<T> clazz, List<T> list, LinkedHashMap<String, String> mapHeaderField,
                                        int excelType) {
        Workbook workbook = null;
        if (0 == excelType) {
            workbook = new HSSFWorkbook();
        } else {
            workbook = new XSSFWorkbook();
        }
        Sheet sheet = workbook.createSheet();

        //设置标题单元格样式
        CellStyle headCellStyle = workbook.createCellStyle();
        headCellStyle.setAlignment(HorizontalAlignment.CENTER);
        headCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        headCellStyle.setFillForegroundColor(IndexedColors.SEA_GREEN.getIndex());
        headCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        Font font = workbook.createFont();
        font.setBold(true);
        headCellStyle.setFont(font);
        //创建标题行
        Row headRow = sheet.createRow(0);
        int colIndex = 0;
        for (Map.Entry entry : mapHeaderField.entrySet()) {
            Cell cell = headRow.createCell(colIndex);
            cell.setCellStyle(headCellStyle);
            cell.setCellValue(entry.getKey().toString());
            colIndex++;
        }
        //设置单元格样式
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.LEFT);
        cellStyle.setVerticalAlignment(VerticalAlignment.TOP);
        cellStyle.setWrapText(true);
        int rowIndex = 1;
        for (T t : list) {
            Row row = sheet.createRow(rowIndex);
            colIndex = 0;
            for (Map.Entry entry : mapHeaderField.entrySet()) {
                Cell cell = row.createCell(colIndex);
                cell.setCellStyle(cellStyle);
                Object fieldValue = ReflectionUtils.getFieldValue(t, entry.getValue().toString());
                cell.setCellValue((null == fieldValue ? "" : fieldValue.toString()));
                colIndex++;
            }
            rowIndex++;
        }
        byte[] bytes = null;
        OutputStream out = null;

        try {
            out = new ByteArrayOutputStream();
            workbook.write(out);
            bytes = ((ByteArrayOutputStream) out).toByteArray();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bytes;
    }

    /**
     * =================================================================================================================
     */


    //利用注解 PoiFieldProp 得到 mapHeaderField
    private static <T> LinkedHashMap<String, String> getMapHeaderFieldByFieldProp(Class<T> clazz) {
        Map<String, Integer> mapNameColIndexTemp = new HashMap<>();
        Map<String, String> mapHeaderFieldTemp = new HashMap<>();
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(FieldProp.class)) {
                FieldProp fieldProp = field.getAnnotation(FieldProp.class);
                if (null == fieldProp.name() || "".equals(fieldProp.name())) {
                    continue;
                }
                if (fieldProp.colIndex() != -1) {
                    mapNameColIndexTemp.put(fieldProp.name(), fieldProp.colIndex());
                    mapHeaderFieldTemp.put(fieldProp.name(), field.getName());
                }
            }
        }

        List<Map.Entry<String, Integer>> list = new ArrayList<>(mapNameColIndexTemp.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return -o2.getValue().compareTo(o1.getValue());
            }
        });

        LinkedHashMap<String, Integer> mapNameColIndex = new LinkedHashMap<>();
        for (Map.Entry entry : list) {
            mapNameColIndex.put(entry.getKey().toString(), Integer.valueOf(entry.getValue().toString()));
        }
        LinkedHashMap<String, String> mapHeaderField = new LinkedHashMap<>();
        for (Map.Entry entry : mapNameColIndex.entrySet()) {
            mapHeaderField.put(entry.getKey().toString(), mapHeaderFieldTemp.get(entry.getKey()));

        }
        return mapHeaderField;
    }

}
