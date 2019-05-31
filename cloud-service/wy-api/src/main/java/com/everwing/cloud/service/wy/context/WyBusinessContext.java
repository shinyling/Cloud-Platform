package com.everwing.cloud.service.wy.context;

import com.everwing.cloud.common.entity.Account;

/**
 * @author DELL shiny
 * @create 2019/5/10
 */
public class WyBusinessContext {

    private static ThreadLocal<Account> threadLocal=new ThreadLocal<>();

    public static void setAccount(Account account){
        threadLocal.set(account);
    }

    public static String getCompanyId(){
        return threadLocal.get().getCompanyId();
    }
}
