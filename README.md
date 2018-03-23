# http-jdbc
- all jdbc support.
- configuration auto refresh.

## 配置文件
- 环境变量
```shell
export HTTPJDBC_CONF=/etc/http-jdbc/

#默认配置文件
$HTTPJDBC_CONF/jdbc.json
```

- 配置文件示例
```json
{
    "config": [
        {
            "alias": "bi.beijing", 
            "driver": "oracle.jdbc.driver.OracleDriver", 
            "url": "jdbc:oracle:thin:@192.168.1.100:1521/ORCL", 
            "host": "192.168.1.100", 
            "port": 1521, 
            "user": "orcl", 
            "password": "orcl", 
            "database": "ORCL", 
            "other": null, 
            "driverClass": "com.github.smartbooks.httpjdbc.core.QueryService"
        }
    ]
}
```

## 查询示例
```json
POST http://localhost:8080/http-jdbc-server/jdbc/sql
{
    "alias": "bi.beijing", 
    "sql": "select * from fact_day_dau where rownum<3", 
    "token": "", 
    "ttl": 0
}

响应结果:
{
    "msg": "ok",
    "code": 100,
    "data": [
		[129,"2018-01-02T00:00:00",188926],
        [72,"2017-12-03T00:00:00",271913]
    ],
    "meta": [
        { "name": "ID",         "type": "NUMBER"},
        { "name": "TOTAL_DATE", "type": "DATE" },
        { "name": "DAU",        "type": "NUMBER"}
    ]
}
```

## 查询别名
```shell
curl http://localhost:8080/http-jdbc-server/jdbc/alias

响应结果:
{
    "msg": "ok",
    "code": 100,
    "data": [
        "bi.hangzhou",
        "bi.shanghai",
        "bi.beijing"
    ]
}
```
