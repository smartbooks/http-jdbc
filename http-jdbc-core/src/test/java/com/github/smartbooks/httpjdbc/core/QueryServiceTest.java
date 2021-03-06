package com.github.smartbooks.httpjdbc.core;

import org.junit.Test;

import java.sql.SQLException;

public class QueryServiceTest
{
    @Test
    public void testConfigManage()
            throws SQLException, ClassNotFoundException, InterruptedException
    {
        ConfigManage configManage = new ConfigManage();

        configManage.config.add(getJdbcProperty());

        ConfigManage.save(configManage);

        ConfigManage jsonLaod = ConfigManage.load();

        System.out.println(jsonLaod);

        //test service
        QueryServiceManage.init(jsonLaod);

        QueryService service = QueryServiceManage.getQueryService("bi");

        QueryResult rs = service.excute("SELECT * FROM FACT_DOWNLOAD WHERE ROWNUM < 5");

        System.out.println(rs);

        ConfigWatch cw = new ConfigWatch();
        cw.start();

        while (true) {
            Thread.sleep(2000L);
        }
    }

    @Test
    public void testQuery()
            throws SQLException, ClassNotFoundException
    {
        String sql = "SELECT * FROM FACT_DOWNLOAD WHERE ROWNUM < 5";

        QueryService service = new QueryService(getJdbcProperty());

        QueryResult qs = service.excute(sql);

        System.out.println(qs);
    }

    public JdbcProperty getJdbcProperty()
    {
        JdbcProperty conf = new JdbcProperty();
        conf.setAlias("bi");
        conf.setDatabase("ORCL");
        conf.setUrl("jdbc:oracle:thin:@192.168.1.221:1521/ORCL");
        conf.setDriver("oracle.jdbc.driver.OracleDriver");
        conf.setHost("192.168.1.221");
        conf.setPort(1521);
        conf.setUser("rd_zslbi");
        conf.setPassword("iblsz");
        conf.setDriverClass("com.github.smartbooks.httpjdbc.core.QueryService");
        return conf;
    }
}
