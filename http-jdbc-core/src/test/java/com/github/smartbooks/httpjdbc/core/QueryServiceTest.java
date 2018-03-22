package com.github.smartbooks.httpjdbc.core;

import org.junit.Test;

import java.sql.SQLException;

public class QueryServiceTest
{
    @Test
    public void testQuery()
            throws SQLException, ClassNotFoundException
    {
        String sql = "SELECT * FROM FACT_DOWNLOAD WHERE ROWNUM < 5";

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

        System.out.println(qs);
    }
}