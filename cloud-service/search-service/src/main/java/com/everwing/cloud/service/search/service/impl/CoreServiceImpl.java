package com.everwing.cloud.service.search.service.impl;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.request.CollectionAdminRequest;
import org.apache.solr.client.solrj.response.CollectionAdminResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.everwing.cloud.common.vo.UserVo;
import com.everwing.cloud.service.search.service.CoreService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author DELL shiny
 * @create 2020/3/9
 */
@Slf4j
@Service
public class CoreServiceImpl implements CoreService {

    @Autowired
    private SolrClient solrClient;

    @Override
    public boolean addCore() {
        try {
            Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
            UserVo user= JSON.parseObject(JSON.toJSONString(authentication.getPrincipal()),UserVo.class);
            String coreName=user.getCompanyId();
            CollectionAdminRequest.Create create=CollectionAdminRequest.createCollection(coreName,1,1);
            CollectionAdminResponse response=create.process(solrClient);
            log.debug("coreName:[{}]创建成功!res:[{}]",coreName,response);
        } catch (Exception e) {
            log.error("发生错误!",e);
            return false;
        }
        return true;
    }
}
