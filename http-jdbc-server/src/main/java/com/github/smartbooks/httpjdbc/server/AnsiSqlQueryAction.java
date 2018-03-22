package com.github.smartbooks.httpjdbc.server;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

import java.util.HashMap;
import java.util.Map;

public class AnsiSqlQueryAction
        extends ActionSupport
{
    public Map<String, Object> getJsonData()
    {
        return jsonData;
    }

    public void setJsonData(Map<String, Object> jsonData)
    {
        this.jsonData = jsonData;
    }

    private Map<String, Object> jsonData;

    @Override
    public String execute()
    {
        jsonData = new HashMap<>();
        jsonData.put("data", "none");
        jsonData.put("meta", "none");

        return Action.SUCCESS;
    }
}
