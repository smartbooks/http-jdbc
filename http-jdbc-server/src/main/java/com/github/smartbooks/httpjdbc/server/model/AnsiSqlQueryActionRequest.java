package com.github.smartbooks.httpjdbc.server.model;

/**
 * 查询参数
 *
 * @author smartbooks@qq.com
 */
public class AnsiSqlQueryActionRequest {

    /**
     * 查询sql
     */
    private String sql;

    /**
     * ttl>0:开启缓存,优先读取缓存,不存在则从数据库查询 ttl<0:强制查询,并更新缓存
     */
    private Long ttl;

    /**
     * 数据库实例别名
     */
    private String alias;

    /**
     * 授权标识
     */
    private String token;

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public Long getTtl() {
        return ttl;
    }

    public void setTtl(Long ttl) {
        this.ttl = ttl;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
