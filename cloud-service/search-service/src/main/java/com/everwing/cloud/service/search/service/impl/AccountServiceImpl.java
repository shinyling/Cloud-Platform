package com.everwing.cloud.service.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.everwing.cloud.common.vo.UserVo;
import com.everwing.cloud.service.search.entity.AccountSO;
import com.everwing.cloud.service.search.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * @author DELL shiny
 * @create 2020/3/9
 */
@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private SolrTemplate solrTemplate;

    @Override
    public void addAccount(AccountSO accountSO) {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        UserVo user=JSON.parseObject(JSON.toJSONString(authentication.getPrincipal()),UserVo.class);
        String companyId=user.getCompanyId();
        //通过companyId关联到coreName
        UpdateResponse response=solrTemplate.saveBean(companyId,accountSO);
        solrTemplate.commit(companyId);
        log.info("保存账户信息成功!:[{}]",response);
    }
}
