package com.everwing.cloud.service.platform.anno;

import java.lang.annotation.*;

/**
 * @author DELL
 * @title: SysLog
 * @projectName cloud-platform
 * @description: TODO
 * @date 2020/3/1316:02
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface SysLog {

    String value() default "";

}
