package com.github.smartbooks.httpjdbc.server.servlet;

import com.github.smartbooks.httpjdbc.core.ConfigManage;
import com.github.smartbooks.httpjdbc.core.ConfigWatch;
import com.github.smartbooks.httpjdbc.core.QueryServiceManage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class ConfigLoadServlet
        extends HttpServlet
{
    ConfigWatch configWatch;

    @Override
    public void init()
            throws ServletException
    {
        super.init();

        ConfigManage configManage = ConfigManage.load();

        QueryServiceManage.init(configManage);

        configWatch = new ConfigWatch();
        
        configWatch.start();
    }

    @Override
    public void destroy()
    {
        super.destroy();

        configWatch.stop();
    }
}
