package com.everwing.cloud.service.search.config;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.request.CoreAdminRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.solr.core.SolrTemplate;

/**
 * @author DELL shiny
 * @create 2020/3/9
 */
@Configuration
public class SearchConfig {

    @Value("${spring.data.solr.host}")
    private String host;

    @Bean
    @ConditionalOnMissingBean(SolrTemplate.class)
    public SolrTemplate solrTemplate(SolrClient solrClient) {
        return new SolrTemplate(solrClient);
    }

    @Bean
    public CoreAdminRequest coreAdminRequest(){
        return new CoreAdminRequest();
    }
}
