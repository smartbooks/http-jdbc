package com.github.smartbooks.httpjdbc.core;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ConfigManage
{
    private static final String filename = "jdbc.json";

    public List<JdbcProperty> config = new ArrayList<>();

    public static ConfigManage load()
    {
        String jsonText = FileUtil.readFile(new File(filename));
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
