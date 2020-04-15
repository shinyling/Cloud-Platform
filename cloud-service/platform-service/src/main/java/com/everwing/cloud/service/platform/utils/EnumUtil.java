package com.everwing.cloud.service.platform.utils;

import com.everwing.cloud.service.platform.enums.IsLockEnum;
import com.everwing.cloud.service.platform.enums.MenuParentEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author DELL shiny
 * @date 2020/4/13
 */
public class EnumUtil {

    public static List<Map<String, Object>> convertLockEnumToSelectData() {
        IsLockEnum[] isLockEnums = IsLockEnum.values();
        List<Map<String, Object>> selectData = new ArrayList<>(isLockEnums.length);
        for (int i = 0; i < isLockEnums.length; i++) {
            Map<String, Object> select = new HashMap<>(2);
            select.put("code", isLockEnums[i].getCode());
            select.put("msg", isLockEnums[i].getMsg());
            selectData.add(select);
        }
        return selectData;
    }

    public static List<Map<String, Object>> convertParentEnumToSelectData() {
        MenuParentEnum[] isParentEnums = MenuParentEnum.values();
        List<Map<String, Object>> selectData = new ArrayList<>(isParentEnums.length);
        for (int i = 0; i < isParentEnums.length; i++) {
            Map<String, Object> select = new HashMap<>(2);
            select.put("code", isParentEnums[i].getCode());
            select.put("msg", isParentEnums[i].getMsg());
            selectData.add(select);
        }
        return selectData;
    }
}
