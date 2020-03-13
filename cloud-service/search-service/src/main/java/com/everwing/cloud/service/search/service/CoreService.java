package com.everwing.cloud.service.search.service;

/**
 * @author DELL
 * @title: CoreService
 * @projectName cloud-platform
 * @description: TODO
 * @date 2020/3/915:36
 */
public interface CoreService {
    /**
     * 只在solr cloud 模式下有效
     * @return
     */
    boolean addCore();
}
