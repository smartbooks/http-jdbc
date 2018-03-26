package com.github.smartbooks.httpjdbc.core.cache;

/**
 * 抽象缓存服务
 *
 * @author smartbooks@qq.com
 */
public abstract class BaseCacheService {

    /**
     * 初始化缓存
     */
    public abstract void init();

    /**
     * 根据KEY获取缓存Value
     *
     * @param key
     * @return
     */
    public abstract String getKey(String key);

    /**
     * 根据Key删除缓存Item
     *
     * @param key
     * @return
     */
    public abstract boolean delKey(String key);

    /**
     * 根据Key设置缓存Value
     *
     * @param key
     * @param value
     * @param ttl
     * @return
     */
    public abstract boolean setKey(String key, String value, long ttl);

}
