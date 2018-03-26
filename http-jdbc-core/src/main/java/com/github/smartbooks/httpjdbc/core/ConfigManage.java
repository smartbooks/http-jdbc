package com.github.smartbooks.httpjdbc.core;

import com.github.smartbooks.httpjdbc.core.util.StringUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 配置管理
 *
 * @author smartbooks@qq.com
 */
public class ConfigManage {

    /**
     * 默认配置文件
     */
    public static String filename = "";

    /**
     * 配置文件目录
     */
    public static String HTTPJDBC_CONF;

    /**
     * 配置对象实例
     */
    public static ConfigManage Instance = null;

    static {
        HTTPJDBC_CONF = System.getenv("HTTPJDBC_CONF");
        if (null == HTTPJDBC_CONF || HTTPJDBC_CONF.equals(StringUtil.STRING_EMPTY)) {
            HTTPJDBC_CONF = System.getProperty("user.dir");
        }
        filename = String.format("%s/jdbc.json", HTTPJDBC_CONF);
    }

    /**
     * jdbc连接清单
     */
    public List<JdbcProperty> config = new ArrayList<>();

    /**
     * Redis缓存主机
     */
    public String redisHost = "localhost";

    /**
     * Redis缓存端口
     */
    public Integer redisPort = 6379;

    /**
     * Redis缓存数据库索引
     */
    public int redisDBIndex = 0;

    /**
     * token列表
     */
    public List<String> token = new ArrayList<>();

    /**
     * 加载配置
     *
     * @param file
     * @return
     */
    public static ConfigManage load(File file) {
        String jsonText = FileUtil.readFile(file);
        Instance = (ConfigManage)JsonUtil.toObject(jsonText, ConfigManage.class);
        return Instance;
    }

    /**
     * 加载配置
     *
     * @return
     */
    public static ConfigManage load() {
        File confFile = new File(filename);
        String jsonText = FileUtil.readFile(confFile);
        Instance = (ConfigManage)JsonUtil.toObject(jsonText, ConfigManage.class);
        return Instance;
    }

    /**
     * 保存配置
     *
     * @param configManage
     */
    public static void save(ConfigManage configManage) {
        String jsonText = JsonUtil.toJson(configManage, true);
        FileUtil.writeFile(new File(filename), jsonText);
    }

    @Override
    public String toString() {
        return JsonUtil.toJson(this, true);
    }
}
