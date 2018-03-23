package com.github.smartbooks.httpjdbc.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil
{
    public static String toJson(Object obj)
    {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static Object toObject(String json, Class<?> valueType)
    {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(json, valueType);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
