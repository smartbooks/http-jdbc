package com.github.smartbooks.httpjdbc.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class ConfigManage
{
    public static String filename = "";
    public static String HTTPJDBC_CONF;

    static {
        HTTPJDBC_CONF = System.getenv("HTTPJDBC_CONF");
        if (null == HTTPJDBC_CONF || HTTPJDBC_CONF.equals("")) {
            HTTPJDBC_CONF = System.getProperty("user.dir");
        }
        filename = String.format("%s/jdbc.json", HTTPJDBC_CONF);
    }

    public List<JdbcProperty> config = new ArrayList<>();

    public static ConfigManage load(File file)
    {
        String jsonText = FileUtil.readFile(file);

        return (ConfigManage) JsonUtil.toObject(jsonText, ConfigManage.class);
    }

    public static ConfigManage load()
    {
        File confFile = new File(filename);

        String jsonText = FileUtil.readFile(confFile);

        return (ConfigManage) JsonUtil.toObject(jsonText, ConfigManage.class);
    }

    public static void save(ConfigManage configManage)
    {
        String jsonText = JsonUtil.toJson(configManage);

        FileUtil.writeFile(new File(filename), jsonText);
    }

    @Override
    public String toString()
    {
        return JsonUtil.toJson(this);
    }
}
