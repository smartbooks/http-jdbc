package com.github.smartbooks.httpjdbc.server;

import com.github.smartbooks.httpjdbc.core.JdbcProperty;
import com.github.smartbooks.httpjdbc.core.QueryResult;
import com.github.smartbooks.httpjdbc.core.QueryService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
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
        HttpServletRequest request = ServletActionContext.getRequest();
        System.out.println(request.getMethod());

        String sql = "SELECT * FROM FACT_DOWNLOAD WHERE ROWNUM < 5";

        if (request.getMethod().equals("POST")) {
            //sql = request.getParameterMap().get("sql")[0];
            sql = "";

            BufferedReader br;
            try {
                br = request.getReader();
                String str;
                while ((str = br.readLine()) != null) {
                    sql += str;
                }
                br.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        JdbcProperty conf = new JdbcProperty();
        conf.setAlias("bi");
        conf.setDatabase("ORCL");
        conf.setUrl("jdbc:oracle:thin:@192.168.1.221:1521/ORCL");
        conf.setDriver("oracle.jdbc.driver.OracleDriver");
        conf.setHost("192.168.1.221");
        conf.setPort(1521);
        conf.setUser("rd_zslbi");
        conf.setPassword("iblsz");

        QueryService service = new QueryService(conf);

        QueryResult qs = service.Excute(sql);

        jsonData = new HashMap<>();
        jsonData.put("meta", qs.Meta);
        jsonData.put("data", qs.Data);

        return Action.SUCCESS;
    }
}
