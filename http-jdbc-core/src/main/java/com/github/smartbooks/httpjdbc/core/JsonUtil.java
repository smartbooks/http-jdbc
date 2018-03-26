package com.github.smartbooks.httpjdbc.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Json序列化助手
 *
 * @author smartbooks@qq.com
 */
public class JsonUtil {

    /**
     * 对象转JSon
     *
     * @param obj
     * @param pretty
     * @return
     */
    public static String toJson(Object obj, boolean pretty) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            if (pretty) {
                return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
            } else {
                return mapper.writeValueAsString(obj);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * json转对象
     *
     * @param json
     * @param valueType
     * @return
     */
    public static Object toObject(String json, Class<?> valueType) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(json, valueType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
