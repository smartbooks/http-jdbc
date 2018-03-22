package com.github.smartbooks.httpjdbc.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class QueryResult
{
    public List<ColumnMeta> Meta = new ArrayList<>();

    public List<Object[]> Data = new ArrayList<>();

    @Override
    public String toString()
    {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }
}
