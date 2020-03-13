package com.everwing.cloud.service.search.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.solr.common.cloud.SolrZkClient;
import org.apache.solr.common.cloud.ZkConfigManager;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 对solrcloud 的操作暂时不用
 * @author DELL shiny
 * @create 2020/3/10
 */
@Slf4j
public class CollectionManager {

    private final static String CONFIG_PATH = "/tableConfig/";

    private final static String DEFAULTTABLECONFIG = "defaultTable";

    /**
     * 下载默认配置
     * @throws Exception
     */
//public static void downDefaultConfig() throws Exception {
//downConfig(DEFAULTTABLECONFIG);
//log.info("***下载默认配置***");
//}

    /**
     * 上传默认配置
     *
     * @throws Exception
     */


    public static void upDefaultConfig() throws Exception {
        upConfig(DEFAULTTABLECONFIG);
        log.info("***上传默认配置***");
    }

    /**
     * 下载zk下默认的collectionConfig到本地
     *
     * @param collection 新的配置名前缀
     * @throws Exception
     */


    public static void downConfig(String collection) throws Exception {
        SolrZkClient solrZkClient = ClientManager.getInstance().getSearchClient().getZkStateReader().getZkClient();
        Path path = Paths.get(CONFIG_PATH + collection + "_config");
        solrZkClient.downConfig(DEFAULTTABLECONFIG + "_config", path);
        log.info("***下载zk的collectionConfig到本地【" + collection + "】***");
    }

    /**
     * 上传collectionConfig到zk
     *
     * @param collection
     * @throws Exception
     */


    public static void upConfig(String collection) throws Exception {
        SolrZkClient solrZkClient = ClientManager.getInstance().getSearchClient().getZkStateReader().getZkClient();
        String collectionName = collection + "_config";
        Path path = Paths.get(CONFIG_PATH + collectionName);
        solrZkClient.upConfig(path, collectionName);
        log.info("***上传collectionConfig到zk【" + collection + "】***");
    }

    /**
     * 复制默认配置生成新的collection配置
     *
     * @param collection 新的配置名称
     * @throws Exception
     */


    public static void copyDefaultConfig(String collection) throws Exception {
        ZkConfigManager zkConfigManager = ClientManager.getInstance().getSearchClient().getZkStateReader().getConfigManager();
        String collectionName = collection + "_config";
//solrZkClient.upConfig(path, collectionName);
        zkConfigManager.copyConfigDir(DEFAULTTABLECONFIG + "_config", collectionName);
        log.info("***复制默认配置生成新的collection配置【" + collection + "】***");
    }


    public static void main(String[] args) {
        try {
//upConfig("SA_VW_XGDZ_IN_NN109_TXL");
//System.out.println("ook");
//ClientManager.getInstance().getSearchClient().getZkStateReader().getZkClient().getSolrZooKeeper().getData("/clusterstate.json", null, null);
//JSONObject jsonclusterstate=JSONObject.fromObject(new String(ClientManager.getInstance().getSearchClient().getZkStateReader().getZkClient().getSolrZooKeeper().getData("/clusterstate.json", null, null),"utf-8"));
//System.out.println(jsonclusterstate.toString());
            String collection = "gettingstarted";
//deleteConfig(collection);
//if(!exists(collection)){
//System.out.println("***["+collection+"]配置不存在***");
//copyDefaultConfig(collection);
//}

//SolrZkClient solrZkClient = ClientManager.getInstance().getSearchClient().getZkStateReader().getZkClient();
//Path path=Paths.get(CONFIG_PATH+collection+"_config");
//solrZkClient.downConfig("gettingstarted", path);
//upConfig("gettingstarted");
//upDefaultConfig();
//upConfig("newcollection");
//deleteConfig("newcollection");
//copyDefaultConfig("newcollection");
//upConfig("newcollection2");
            deleteConfig("SA.VW_XGDZ_IN_NNJDCXX");
            System.out.println("ook");
        } catch (Exception e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 检测默认配置是否存在
     *
     * @return
     * @throws Exception
     */


    public static boolean existsDefaultConfig() throws Exception {
        log.info("***检测默认配置是否存在***");
        return exists(DEFAULTTABLECONFIG);
    }

    /**
     * 检测配置是否存在
     *
     * @param collection
     * @return
     * @throws Exception
     */


    public static boolean exists(String collection) throws Exception {
        SolrZkClient solrZkClient = ClientManager.getInstance().getSearchClient().getZkStateReader().getZkClient();
        log.info("***检测配置是否存在【" + collection + "】***");
        return solrZkClient.exists("/configs/" + collection + "_config", false);
    }

    /**
     * 删除collection配置
     *
     * @param collection
     * @throws Exception
     */


    public static void deleteConfig(String collection) throws Exception {
        ZkConfigManager zkConfigManager = ClientManager.getInstance().getSearchClient().getZkStateReader().getConfigManager();
        log.info("***删除collection配置【" + collection + "】***");
        zkConfigManager.deleteConfigDir(collection + "_config");
    }
}
