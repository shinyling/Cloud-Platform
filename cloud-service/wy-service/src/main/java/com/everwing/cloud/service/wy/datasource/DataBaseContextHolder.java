package com.everwing.cloud.service.wy.datasource;

import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DELL shiny
 * @create 2019/5/10
 */
public class DataBaseContextHolder {

    private static final ThreadLocal contextHolder = new ThreadLocal<>();

    public static List<String> companyIds = new ArrayList<String>();

    public static void setCompanyId(final String companyId){
        Assert.notNull(companyId,"companyId can not be null");
        contextHolder.set(companyId);
    }

    public static String getCompanyId(){
        return (String) contextHolder.get();
    }

    public static void clearCompanyId(){
        contextHolder.remove();
    }

    public static boolean containsCompanyId(String companyId){
        return companyIds.contains(companyId);
    }

}
