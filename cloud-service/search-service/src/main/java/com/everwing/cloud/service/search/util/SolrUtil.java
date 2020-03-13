//package com.everwing.cloud.service.search.util;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.solr.client.solrj.SolrServerException;
//import org.apache.solr.client.solrj.request.CollectionAdminRequest;
//import org.apache.solr.client.solrj.request.schema.SchemaRequest;
//import org.apache.solr.client.solrj.response.CollectionAdminResponse;
//import org.apache.solr.client.solrj.response.schema.SchemaResponse;
//import org.apache.solr.common.params.ModifiableSolrParams;
//import org.apache.solr.common.util.NamedList;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * @author DELL shiny
// * @create 2020/3/10
// */
//@Slf4j
//public class SolrUtil {
//
//    public final static String groupFiledNamePostfix = "_single";
//
//    /**
//     * 创建Collection
//     */
//    public static void createCollection(String collection, List<Column> columns) throws Exception {
//        // 创建collection
//        createCollection(collection);
//        // 定义Field
//        createFields(collection, columns);
//        log.debug("CollectionManager.createCollection");
//    }
//
//    public static void createCollection(String collection) throws Exception {
//        if (isExist(collection)) {
//            return;
//        } else {
//            setZKConfig(collection);
//            // 创建Collection
//            // collection不存在
//            CollectionAdminRequest.Create create = CollectionAdminRequest.Create.createCollection(collection, collection + "_config",
//                    3, 2);
//            // 配置Collection
//            create.setMaxShardsPerNode(3);
//            //Properties properties=new Properties();
//            //properties.setProperty("name", collection+"_shard1_replica_n2");
//            //properties.setProperty("schema", "managed-schema"+collection);
//            //properties.setProperty("config", "solrconfig.xml");
//            //create.getProperties();
//            //create.setProperties(properties);
//            //create.setConfigName(collection);
//            CollectionAdminResponse response = create.process(ClientManager.getInstance().getSearchClient());
//            System.out.println(response);
//        }
//        log.debug("CollectionManager.createCollection");
//    }
//
//
//    /**
//     * 根据数据表字段创建Field
//     */
//    public static void createFields(String collection, List<Column> columns) throws Exception {
//        System.out.println("CollectionManager.createFields");
//        List<String> schemaFields = getSchemaFields(collection);
//        List<SchemaRequest.Update> list = new ArrayList<SchemaRequest.Update>();
//        for (Column column : columns) {
//            // schema中没有该字段
//            if (schemaFields != null && !schemaFields.contains(column.getColumnZhName())) {
//                // 增加到字段到Collection
//
//                Map<String, Object> fieldAttributes = new HashMap<>();
//                fieldAttributes.put("name", column.getColumnZhName() + groupFiledNamePostfix);
//                fieldAttributes.put("type", "string");
//                fieldAttributes.put("indexed", "true");
//                fieldAttributes.put("stored", "true");
//                SchemaRequest.AddField addField = new SchemaRequest.AddField(fieldAttributes);
//                //SchemaResponse.UpdateResponse response=addField.process(ClientManager.getInstance().getSearchClient(),collection);
//                list.add(addField);
//                fieldAttributes = new HashMap<>();
//                fieldAttributes.put("name", column.getColumnZhName());
//                fieldAttributes.put("type", "text_general");
//                fieldAttributes.put("indexed", "true");
//                fieldAttributes.put("stored", "true");
//                addField = new SchemaRequest.AddField(fieldAttributes);
//                //response=addField.process(ClientManager.getInstance().getSearchClient(),collection);
//                list.add(addField);
//            }
//        }
//        SchemaRequest.MultiUpdate multiUpdateRequest = new SchemaRequest.MultiUpdate(list);
//        SchemaResponse.UpdateResponse multipleUpdatesResponse = multiUpdateRequest.process(ClientManager.getInstance().getSearchClient(), collection);
//        System.out.println(multipleUpdatesResponse.getRequestUrl() + " : " + multipleUpdatesResponse.getResponseHeader());
//    }
//
//    /**
//     * 使用copyFields创建union域
//     * 添加CopyField 复制域   主要参数source、dest,复制与只要将多个域组合成一个域
//     */
//    public static void createCopyFields(String collection, List<Column> columns) {
//        System.out.println("CollectionManager.createCopyFields");
//        List<String> sorecesCopyFields = getSchemaCopyFields(collection);
//        List<String> destFields = new ArrayList<>();
//        for (Column column : columns) {
//            if (!sorecesCopyFields.contains(column.getColumnZhName())) {
//                destFields.add(column.getColumnZhName() + groupFiledNamePostfix);
//            }
//        }
//        SchemaRequest.AddCopyField addCopyField = new SchemaRequest.AddCopyField("union", destFields);
//
//        try {
//            SchemaResponse.UpdateResponse response = addCopyField.process(ClientManager.getInstance().getSearchClient(), collection);
//            NamedList<Object> result = response.getResponse();
//            System.out.println(result.toString());
//        } catch (SolrServerException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 查看所有copyFields的union域
//     */
//    public static List<String> getSchemaCopyFields(String collection) {
////      String fieldname="name";
//        List<String> sources = new ArrayList<>();
//        ModifiableSolrParams params = new ModifiableSolrParams();
//        params.add("wt", "json");
//        // 设置返回sourcefield信息
//        params.add("source.fl", "name");
//        //params.add("dest.fl","union");
//        SchemaRequest.CopyFields allCopyFields = new SchemaRequest.CopyFields(params);
//        try {
//            SchemaResponse.CopyFieldsResponse response = allCopyFields.process(ClientManager.getInstance().getSearchClient(), collection);
//            NamedList<Object> result = response.getResponse();
//            List<NamedList<Object>> copyFields = (List<NamedList<Object>>) result.get("copyFields");
//            for (NamedList<Object> copyField : copyFields) {
//                for (Map.Entry<String, Object> entry : copyField) {
//                    String key = entry.getKey();
//                    Object val = entry.getValue();
//                    if (key.equals("source")) {
//                        sources.add(val.toString());
//                    }
//                }
//            }
//        } catch (SolrServerException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//        return sources;
//    }
//
//    /**
//     * 删除Field
//     */
//    public static void deleteField(String connection, String fieldName) {
//        SchemaRequest.DeleteField deleteField = new SchemaRequest.DeleteField(fieldName);
//        try {
//            SchemaResponse.UpdateResponse response = deleteField.process(ClientManager.getInstance().getSearchClient(), connection);
//            System.out.println(response.getRequestUrl());
//        } catch (Exception e) {
//            System.out.println(fieldName + "schema文件中的删除失败");
//            e.printStackTrace();
//        }
//
//    }
//
//    /**
//     * 更新Field
//     */
//    public static void updateField(String collection) {
//        Map<String, Object> fieldAttributes = new HashMap<>();
//        fieldAttributes.put("name", "product_name");
//        fieldAttributes.put("type", "date");
//        fieldAttributes.put("stored", "true");
//        fieldAttributes.put("omitNorms", true);
//
//        SchemaRequest.ReplaceField replaceField = new SchemaRequest.ReplaceField(fieldAttributes);
//        try {
//            SchemaResponse.UpdateResponse response = replaceField.process(ClientManager.getInstance().getSearchClient(), collection);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    /**
//     * 检查Collection是否存在
//     */
//    private static boolean isExist(String collection) throws Exception {
//        boolean isConnection = ClientManager.getInstance().getSearchClient().getZkStateReader().getClusterState().hasCollection(collection);
//        if (isConnection) {
//            log.debug(collection + "CollectionManager.isExist");
//        } else {
//            log.debug(collection + "CollectionManager.totExist");
//        }
//        return isConnection;
//    }
//
//    /**
//     * 配置Collection
//     */
//    private static void setZKConfig(String collection) throws Exception {
//        //先创建并且上传collectionConfig
////      CollectionConfigManager.downConfig(collection);
////      CollectionConfigManager.upConfig(collection);
//        if (!CollectionConfigManager.exists(collection)) {
//            CollectionConfigManager.copyDefaultConfig(collection);
//        }
//        System.out.println("CollectionManager.setZKConfig");
//    }
//
//    /**
//     * 返回collection所有Field的定义信息
//     *
//     * @param collection
//     * @return
//     * @throws Exception
//     */
//    private static List<String> getSchemaFields(String collection) throws Exception {
//
//        //System.out.println("CollectionManager.getSchemaFields");
//        List<String> AllField = new ArrayList<>();
//        ModifiableSolrParams params = new ModifiableSolrParams();
//        //是否显示域类型的默认信息
//        params.add("showDefaults", "false");
//        //是否返回动态域的定义信息
//        params.add("includeDynamic", "true");
//        //指定返回那些域的定义信息
//        params.add("f1", "name,_version_");
//        SchemaRequest.Fields allFields = new SchemaRequest.Fields(params);
//        SchemaResponse.FieldsResponse response;
//        try {
//            response = allFields.process(ClientManager.getInstance().getSearchClient(), collection);
//            NamedList<Object> result = response.getResponse();
//            @SuppressWarnings("unchecked")
//            List<NamedList<Object>> fields = (List<NamedList<Object>>) result.get("fields");
//            for (NamedList<Object> field : fields) {
//                for (Map.Entry<String, Object> entry : field) {
//                    String key = entry.getKey();
//                    Object val = entry.getValue();
//                    if (key.equals("name")) {
//                        if (val == null) {
//                            AllField.add("");
//                        } else {
//                            AllField.add(val.toString());
//                        }
//                    }
//                }
//
//            }
//
//        } catch (SolrServerException e) {
//            e.printStackTrace();
//            log.error("返回collection所有Field的定义信息异常!", e);
//        } catch (IOException e) {
//            e.printStackTrace();
//            log.error("返回collection所有Field的定义信息异常!", e);
//        }
//
//        return AllField;
//    }
//
//    /**
//     * 删除collection
//     *
//     * @param collection
//     * @throws Exception
//     */
//    public static void deleteCollection(String collection) throws Exception {
//        if (CollectionManager.isExist(collection)) {
//            System.out.println("***collection:" + collection);
//            CollectionAdminRequest.Delete delete = CollectionAdminRequest.deleteCollection(collection);
//            delete.process(ClientManager.getInstance().getSearchClient());
//        } else {
//            System.out.println("***找不到要删除的collection[" + collection + "]***");
//        }
//    }
//}
