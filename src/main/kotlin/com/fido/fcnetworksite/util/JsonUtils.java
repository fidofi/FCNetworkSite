package com.fido.fcnetworksite.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @author: Fido Wang (fromwxf@gmail.com)
 * @date: 2018/10/27 20:47
 * @description:
 */
public class JsonUtils {

    protected static Logger logger = LoggerFactory.getLogger(JsonUtils.class);

    private static JsonBinder binder = JsonBinder.buildNonDefaultBinder();

    /**
     * List to json
     *
     * @param list
     * @return
     */
    public static String listToJson(List<?> list) {
        return binder.toJson(list);
    }

    /**
     * map to json
     *
     * @param map
     * @return
     */
    public static String mapToJson(Map<?, ?> map) {
        return binder.toJson(map);
    }

    /**
     * obj to json
     *
     * @param obj
     * @return
     */
    public static String objectToJson(Object obj) {
        return binder.toJson(obj);
    }

    /**
     * boolean to json
     *
     * @param bool
     * @return
     */
    public static String booleanToJson(Boolean bool) {
        return binder.toJson(bool);
    }

    /**
     * json to string list
     *
     * @param listString
     * @return
     */
    public static List<String> jsonToStringList(String listString) {
        List<String> liststr = null;
        try {
            liststr = binder.getMapper().readValue(listString, List.class);
        } catch (Exception e) {
            logger.error("json反序列化fail", e);
        }
        return liststr;
    }

    /**
     * json to list obj
     *
     * @param beanStr
     * @return
     */
    public static List<Object> jsonToBean(String beanStr) {
        List<Object> listObj = null;
        try {
            listObj = binder.getMapper().readValue(beanStr, new TypeReference<List<Object>>() {});
        } catch (Exception e) {
            logger.error("json反序列化失败", e);
        }
        return listObj;
    }

    /**
     * json to map
     *
     * @param mapStr
     * @return
     */
    public static Map<String, Object> jsonToMap(String mapStr) {
        return binder.fromJson(mapStr, HashMap.class);
    }

    /**
     * json to bean
     *
     * @param <T>
     * @param jsonStr
     * @param clazz
     * @return
     */
    public static <T> T jsonToBean(String jsonStr, Class<T> clazz) {
        return binder.fromJson(jsonStr, clazz);
    }

    public static <T> T jsonToBean(Object any, Class<T> clazz) {
        return binder.fromJson(objectToJson(any), clazz);
    }

    /**
     * json字符串转换成对象列表
     *
     * @param jsonStr json字符串
     * @param clazz 列表元素类
     * @param <T> 列表元素类型
     * @return 转换成功则返回对象列表，否则返回空列表
     */
    public static <T> List<T> jsonToList(String jsonStr, Class<T> clazz) {
        List<T> list = Collections.emptyList();

        if (StringUtils.isBlank(jsonStr)) {
            return list;
        }

        try {
            JavaType type =
                    binder.getMapper()
                            .getTypeFactory()
                            .constructParametrizedType(ArrayList.class, List.class, clazz);
            list = binder.getMapper().readValue(jsonStr, type);
        } catch (Exception ex) {
            logger.error("json反序列化失败", ex);
        }
        return list;
    }

  public static <T> List<T> jsonToList(Object any, Class<T> clazz) {
    List<T> list = Collections.emptyList();

    String jsonStr = binder.toJson(any);

    if (StringUtils.isBlank(jsonStr)) {
      return list;
    }

    try {
      JavaType type = binder.getMapper().getTypeFactory()
              .constructParametrizedType(ArrayList.class, List.class, clazz);
      list = binder.getMapper().readValue(jsonStr, type);
    } catch (Exception ex) {
      logger.error("json反序列化失败", ex);
    }
    return list;
  }

    public static <T> T beanToBean(Object any, Class<T> clazz) {
        String jsonStr = binder.toJson(any);

        if (StringUtils.isBlank(jsonStr)) {
            return null;
        }

        return jsonToBean(jsonStr, clazz);
    }
}
