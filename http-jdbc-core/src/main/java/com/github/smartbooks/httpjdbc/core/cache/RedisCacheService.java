package com.github.smartbooks.httpjdbc.core.cache;

import com.github.smartbooks.httpjdbc.core.ConfigManage;
import redis.clients.jedis.Jedis;

/**
 * Redis缓存实现
 *
 * @author smartbooks@qq.com
 */
public class RedisCacheService extends BaseCacheService {

    private Jedis jedis;

    public RedisCacheService() {
        init();
    }

    @Override
    public void init() {
        jedis = new Jedis(ConfigManage.Instance.redisHost, ConfigManage.Instance.redisPort);
        jedis.select(ConfigManage.Instance.redisDBIndex);
    }

    @Override
    public String getKey(String key) {
        return jedis.get(key);
    }

    @Override
    public boolean delKey(String key) {
        jedis.del(key);
        return true;
    }

    @Override
    public boolean setKey(String key, String value, long ttl) {
        return jedis.set(key, value).equals(null) == false;
    }
}
