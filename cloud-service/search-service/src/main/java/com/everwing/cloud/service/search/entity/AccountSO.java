package com.everwing.cloud.service.search.entity;

import lombok.Data;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

/**
 * @author DELL shiny
 * @create 2020/3/9
 */
@Data
@SolrDocument
public class AccountSO {

    @Indexed("id")
    private String id;

    @Indexed("name")
    private String name;

    @Indexed("age")
    private Integer age;
}
