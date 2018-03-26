package com.github.smartbooks.httpjdbc.core;

import java.util.HashMap;

/**
 * @author smartbooks@qq.com
 */
public class JdbcProperty
{
    private String alias;
    private String driver;
    private String url;
    private String host;
    private Integer port;
    private String user;
    private String password;
    private String database;
    private HashMap<String, String> other;
    private String driverClass;

    public String getDriverClass()
    {
        return driverClass;
    }

    public void setDriverClass(String driverClass)
    {
        this.driverClass = driverClass;
    }

    public String getAlias()
    {
        return alias;
    }

    public void setAlias(String alias)
    {
        this.alias = alias;
    }

    public String getDriver()
    {
        return driver;
    }

    public void setDriver(String driver)
    {
        this.driver = driver;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getHost()
    {
        return host;
    }

    public void setHost(String host)
    {
        this.host = host;
    }

    public Integer getPort()
    {
        return port;
    }

    public void setPort(Integer port)
    {
        this.port = port;
    }

    public String getUser()
    {
        return user;
    }

    public void setUser(String user)
    {
        this.user = user;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getDatabase()
    {
        return database;
    }

    public void setDatabase(String database)
    {
        this.database = database;
    }

    public HashMap<String, String> getOther()
    {
        return other;
    }

    public void setOther(HashMap<String, String> other)
    {
        this.other = other;
    }
}
