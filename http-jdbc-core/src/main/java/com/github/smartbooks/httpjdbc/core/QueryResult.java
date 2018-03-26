package com.github.smartbooks.httpjdbc.core;

import java.util.ArrayList;
import java.util.List;

/**
 * 查询结果
 *
 * @author smartbooks@qq.com
 */
public class QueryResult {

    /**
     * 元数据信息
     */
    public List<ColumnMeta> meta = new ArrayList<>();

    /**
     * 查询结果数据
     */
    public List<Object[]> data = new ArrayList<>();

    @Override
    public String toString() {
        return JsonUtil.toJson(this, true);
    }
}
