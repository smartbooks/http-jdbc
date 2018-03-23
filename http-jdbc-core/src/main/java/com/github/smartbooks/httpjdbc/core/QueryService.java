package com.github.smartbooks.httpjdbc.core;

import java.sql.*;

public class QueryService
{
    private JdbcProperty conf;

    public QueryService(JdbcProperty conf)
    {
        this.conf = conf;
    }

    public String Alias()
    {
        return conf.getAlias();
    }

    public QueryResult Excute(String sql)
            throws SQLException, ClassNotFoundException
    {
        synchronized (this) {

            QueryResult qr = new QueryResult();
            Connection connection = null;

            try {

                connection = getConnection(conf);

                Statement statement = connection.createStatement();

                ResultSet rs = statement.executeQuery(sql);

                ResultSetMetaData rsd = rs.getMetaData();

                int columnCount = rsd.getColumnCount();

                for (int i = 0; i < columnCount; i++) {
                    ColumnMeta col = new ColumnMeta();
                    col.setName(rsd.getColumnName(i + 1));
                    col.setType(rsd.getColumnTypeName(i + 1));
                    qr.Meta.add(col);
                }

                while (rs.next()) {
                    Object[] row = new Object[columnCount];
                    for (int i = 0; i < columnCount; i++) {
                        row[i] = rs.getObject(i + 1);
                    }
                    qr.Data.add(row);
                }

                rs.close();
                statement.close();
            }
            finally {
                if (null != connection) {
                    try {
                        connection.close();
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            return qr;
        }
    }

    public Connection getConnection(JdbcProperty conf)
            throws SQLException, ClassNotFoundException
    {
        Class.forName(conf.getDriver());
        return DriverManager.getConnection(conf.getUrl(), conf.getUser(), conf.getPassword());
    }
}
