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
            "alias": "bi.rpt", 
            "driver": "oracle.jdbc.driver.OracleDriver", 
            "url": "jdbc:oracle:thin:@host:1521/ORCL", 
            "host": "host", 
            "port": 1521, 
            "user": "orcl", 
            "password": "", 
            "database": "ORCL", 
            "other": null, 
            "driverClass": "com.github.smartbooks.httpjdbc.core.QueryService"
        },{
            "alias": "bi.presto",
            "driver": "com.facebook.presto.jdbc.PrestoDriver",
            "url": "jdbc:presto://host:8080/catalog/schema",
            "host": "host",
            "port": 8080,
            "user": "root",
            "password": "",
            "database": "schema",
            "other": null,
            "driverClass": "com.github.smartbooks.httpjdbc.core.QueryService"
        },{
            "alias": "bi.mysql",
            "driver": "com.mysql.jdbc.Driver",
            "url": "jdbc:mysql://host:3306/database?name=value",
            "host": "host",
            "port": 3306,
            "user": "root",
            "password": "",
            "database": "database",
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
        "bi.rpt",
        "bi.presto",
        "bi.mysql"
    ]
}
```

## Presto
```shell

curl http://localhost:8090/http-jdbc-server/jdbc/alias

#查询presto on oracle
curl -H "Content-Type: application/json" -X POST -d '{"alias":"presto","sql":"select * from oracle.schema.sys_menu","token":"","ttl":0}' http://localhost:8090/http-jdbc-server/jdbc/sql

#查询presto on mongodb
curl -H "Content-Type: application/json" -X POST -d '{"alias":"presto","sql":"select * from mongodb.db.collection limit 10","token":"","ttl":0}' http://localhost:8090/http-jdbc-server/jdbc/sql

#查询presto on hive
curl -H "Content-Type: application/json" -X POST -d '{"alias":"presto","sql":"select * from hive.db.table limit 10","token":"","ttl":0}' http://localhost:8090/http-jdbc-server/jdbc/sql

#查询oracle
curl -H "Content-Type: application/json" -X POST -d '{"alias":"bi.zsl.rpt","sql":"select * from table where rownum < 5","token":"","ttl":0}' http://localhost:8090/http-jdbc-server/jdbc/sql
```
