package com.everwing.cloud.service.wy.datasource;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author DELL shiny
 * @create 2019/5/29
 */
public class DataSourceUtil {

    public static final String DEFAULT = "1232590503586938882";

    private static final Map<String, String> dataSourceMap = new ConcurrentHashMap<>();

    private DataSourceUtil() {
    }

    public static void put(String companyId, String dataSource) {
        dataSourceMap.put(companyId, dataSource);
    }

    public static boolean contains(String companyId) {
        return dataSourceMap.get(companyId) != null;
    }

    public static String getDataSourceId(String companyId) {
        return dataSourceMap.get(companyId);
    }
}
