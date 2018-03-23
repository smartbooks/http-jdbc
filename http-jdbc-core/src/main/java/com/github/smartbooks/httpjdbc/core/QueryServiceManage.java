package com.github.smartbooks.httpjdbc.core;

import java.lang.reflect.Constructor;
import java.util.*;

public class QueryServiceManage
{
    private static Map<String, QueryService> serviceMap = new HashMap<>();

    public static QueryService getQueryService(String alias)
    {
        return serviceMap.get(alias);
    }

    public static List<String> getAlias()
    {
        List<String> aliasList = new ArrayList<>();

        Iterator<String> it = serviceMap.keySet().iterator();
        
        while (it.hasNext()) {
            aliasList.add(it.next());
        }

        return aliasList;
    }

    public static void init(ConfigManage configManage)
    {
        synchronized (serviceMap) {
            serviceMap.clear();

            for (JdbcProperty item : configManage.config) {
                try {
                    Class<?> serviceClass = Class.forName(item.getDriverClass());

                    Constructor cl = serviceClass.getDeclaredConstructor(JdbcProperty.class);

                    QueryService serviceInstance = (QueryService) cl.newInstance(item);

                    serviceMap.put(item.getAlias(), serviceInstance);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
