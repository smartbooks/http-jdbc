package com.github.smartbooks.httpjdbc.server;

import com.github.smartbooks.httpjdbc.core.JsonUtil;
import com.github.smartbooks.httpjdbc.core.QueryResult;
import com.github.smartbooks.httpjdbc.core.QueryService;
import com.github.smartbooks.httpjdbc.core.QueryServiceManage;
import com.github.smartbooks.httpjdbc.server.model.AnsiSqlQueryActionRequest;
import com.github.smartbooks.httpjdbc.server.util.ServletUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
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

    public String sql()
    {
        jsonData = new HashMap<>();

        HttpServletRequest request = ServletActionContext.getRequest();

        if (request.getMethod().equals("POST")) {
            try {
                String requestBody = ServletUtil.ReadPostBody(request);

                //validate request
                if (requestBody.equals("")) {
                    throw new Exception("invalid request");
                }

                AnsiSqlQueryActionRequest model = (AnsiSqlQueryActionRequest) JsonUtil.toObject(requestBody, AnsiSqlQueryActionRequest.class);

                if (null == model) {
                    throw new Exception(String.format("invalid request body:%s", requestBody));
                }

                //token validate
                //ttl cache validate

                if (model.getSql().equals("")) {
                    throw new Exception("empty sql");
                }

                QueryService queryService = QueryServiceManage.getQueryService(model.getAlias());

                if (null == queryService) {
                    throw new Exception(String.format("QueryService not found:%s", model.getAlias()));
                }

                QueryResult qs = queryService.Excute(model.getSql());

                jsonData.put("meta", qs.Meta);
                jsonData.put("data", qs.Data);
                jsonData.put("code", 100);
                jsonData.put("msg", "ok");
            }
            catch (Exception e) {
                jsonData.put("code", 500);
                jsonData.put("msg", e.toString());
            }
        }
        else {
            jsonData.put("code", 403);
            jsonData.put("msg", "Only support Post.");
        }

        return Action.SUCCESS;
    }

    public String alias()
    {
        jsonData = new HashMap<>();
        jsonData.put("data", QueryServiceManage.getAlias());
        jsonData.put("code", 100);
        jsonData.put("msg", "ok");

        return Action.SUCCESS;
    }
}
