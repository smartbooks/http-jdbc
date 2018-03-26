package com.github.smartbooks.httpjdbc.server;

import com.github.smartbooks.httpjdbc.core.*;
import com.github.smartbooks.httpjdbc.core.cache.BaseCacheService;
import com.github.smartbooks.httpjdbc.core.util.Md5Util;
import com.github.smartbooks.httpjdbc.core.util.StringUtil;
import com.github.smartbooks.httpjdbc.server.model.AnsiSqlQueryActionRequest;
import com.github.smartbooks.httpjdbc.server.util.ServletUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author smartbooks@qq.com
 */
public class AnsiSqlQueryAction
    extends ActionSupport {

    public Map<String, Object> getJsonData() {
        return jsonData;
    }

    public void setJsonData(Map<String, Object> jsonData) {
        this.jsonData = jsonData;
    }

    private Map<String, Object> jsonData;

    /**
     * new RedisCacheService()
     */
    private BaseCacheService cacheService = null;

    public String sql() {
        jsonData = new HashMap<>();

        HttpServletRequest request = ServletActionContext.getRequest();

        if (request.getMethod().equals(StringUtil.HTTP_SUPPORT_METHOD)) {
            try {
                String requestBody = ServletUtil.readPostBody(request);

                //validate request
                if (requestBody.equals(StringUtil.STRING_EMPTY)) {
                    throw new Exception("invalid request");
                }

                AnsiSqlQueryActionRequest model = (AnsiSqlQueryActionRequest)JsonUtil.toObject(requestBody,
                    AnsiSqlQueryActionRequest.class);

                if (null == model) {
                    throw new Exception(String.format("invalid request body:%s", requestBody));
                }

                //validate token
                if (ConfigManage.Instance.token.contains(model.getToken()) == false) {
                    throw new Exception(String.format("invalid token %s", model.getToken()));
                }

                //validate sql
                if (model.getSql().equals(StringUtil.STRING_EMPTY)) {
                    throw new Exception("empty sql");
                }

                //token validate

                QueryService queryService = QueryServiceManage.getQueryService(model.getAlias());
                if (null == queryService) {
                    throw new Exception(String.format("QueryService not found:%s", model.getAlias()));
                }
                QueryResult qs;

                //ttl cache validate
                if (null != cacheService) {
                    String checkKey = Md5Util.toMd5(model.getAlias() + model.getSql());
                    String checkValue;
                    long ttl = model.getTtl();
                    if (ttl > 0) {
                        checkValue = cacheService.getKey(checkKey);
                        if (null == checkValue) {
                            //query
                            qs = queryService.excute(model.getSql());
                            checkValue = JsonUtil.toJson(qs, false);
                            //cache
                            cacheService.setKey(checkKey, checkValue, ttl);
                        } else {
                            qs = (QueryResult)JsonUtil.toObject(checkValue, QueryResult.class);
                        }
                    } else {
                        //query
                        qs = queryService.excute(model.getSql());
                        checkValue = JsonUtil.toJson(qs, false);
                        //cache
                        cacheService.setKey(checkKey, checkValue, Long.MAX_VALUE);
                    }
                } else {
                    qs = queryService.excute(model.getSql());
                }

                jsonData.put("meta", qs.meta);
                jsonData.put("data", qs.data);
                jsonData.put("code", 100);
                jsonData.put("msg", "ok");
            } catch (Exception e) {
                jsonData.put("code", 500);
                jsonData.put("msg", e.toString());
            }
        } else {
            jsonData.put("code", 403);
            jsonData.put("msg", "Only support Post.");
        }

        return Action.SUCCESS;
    }

    public String alias() {
        jsonData = new HashMap<>();
        jsonData.put("data", QueryServiceManage.getAlias());
        jsonData.put("code", 100);
        jsonData.put("msg", "ok");

        return Action.SUCCESS;
    }
}
