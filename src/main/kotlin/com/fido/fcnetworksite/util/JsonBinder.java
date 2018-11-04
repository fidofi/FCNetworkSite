package com.fido.fcnetworksite.util;

/**
 * @author: Fido Wang (fromwxf@gmail.com)
 * @date: 2018/10/27 20:48
 * @description:
 */

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.kotlin.KotlinModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;

/**
 * Jackson简单封装,json mapper.
 *
 * @author Seewo software - Marco.hu(huzhiguo@cvte.cn)
 */
public class JsonBinder {

    private static Logger logger = LoggerFactory.getLogger(JsonBinder.class);

    private ObjectMapper mapper;

    private JsonBinder(Include include) {
        mapper = new ObjectMapper();
        mapper.registerModule(new KotlinModule());
        // 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /** 创建输出全部属性到Json字符串的Binder. */
    public static JsonBinder buildNormalBinder() {
        return new JsonBinder(Include.ALWAYS);
    }

    /** 创建只输出非空属性到Json字符串的Binder. */
    public static JsonBinder buildNonNullBinder() {
        return new JsonBinder(Include.NON_NULL);
    }

    /** 创建只输出初始值被改变的属性到Json字符串的Binder. */
    public static JsonBinder buildNonDefaultBinder() {
        return new JsonBinder(Include.NON_DEFAULT);
    }

    /**
     * 如果JSON字符串为Null或"null"字符串,返回Null. 如果JSON字符串为"[]",返回空集合.
     *
     * <p>如需读取集合如List/Map,且不是List<String>这种简单类型时使用如下语句: List<MyBean> beanList =
     * binder.getMapper().readValue(listString, new TypeReference<List<MyBean>>() {});
     */
    public <T> T fromJson(String jsonString, Class<T> clazz) {
        if (StringUtils.isEmpty(jsonString)) {
            return null;
        }
        try {
            return mapper.readValue(jsonString, clazz);
        } catch (IOException e) {
            logger.warn("parse json string error:" + jsonString, e);
            return null;
        }
    }

    /** 如果对象为Null,返回"null". 如果集合为空集合,返回"[]". */
    public String toJson(Object object) {
        if (object == null) {
            return "";
        }

        try {
            return mapper.writeValueAsString(object);
        } catch (IOException e) {
            logger.warn("write to json string error:" + object, e);
            return null;
        }
    }

    /** 取出Mapper做进一步的设置或使用其他序列化API. */
    public ObjectMapper getMapper() {
        return mapper;
    }
}
