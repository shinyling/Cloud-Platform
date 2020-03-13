package com.everwing.cloud.service.search.util;

import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.impl.ConcurrentUpdateSolrClient;

import java.util.*;

/**
 * @author DELL shiny
 * @create 2020/3/10
 */
public class ClientManager {

    // 连接池大小
    private final static int POOL_SIZE = 3;
    // ZK URL
    private final static String ZK_HOST = "192.168.8.200:9983";
    // private final static String ZK_HOST = "127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183";
    // CloudSolrClient 连接池
    private static List<CloudSolrClient> searchClientPool = new Vector<CloudSolrClient>();

    private static ClientManager instance;

    private ClientManager() {
        try {
            initSolrClientPool();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static synchronized ClientManager getInstance() {
        if (instance == null) {
            instance = new ClientManager();
        }
        return instance;
    }

    /**
     * 初始化SolrClient连接池
     */
    private void initSolrClientPool() throws Exception {
        // 初始化检索用SolrClient连接池
        for (int i = 0; i < POOL_SIZE; i++) {
            searchClientPool.add(makeCloudSolrClient());
        }
    }

    private synchronized CloudSolrClient makeCloudSolrClient() throws Exception {
        final List<String> hosts = new ArrayList<String>();
        hosts.add(ZK_HOST);
        CloudSolrClient.Builder builder = new CloudSolrClient.Builder(hosts, Optional.empty());
        CloudSolrClient client = builder.build();
        //设置collection缓存的存活时间，单位 分钟
        client.setCollectionCacheTTl(2);
        //索引优化
        //client.optimize();
        client.connect();
        return client;
    }

    /**
     * 获取检索用SolrClient
     *
     * @return
     */
    public CloudSolrClient getSearchClient() throws Exception {
        return searchClientPool.get((new Random()).nextInt(POOL_SIZE));
    }

    private String getBaseSolrUrl() throws Exception {
        String baseSolrUrl = null;
        CloudSolrClient client = getSearchClient();
        Set<String> liveNodes = client.getZkStateReader().getClusterState().getLiveNodes();
        if (liveNodes != null) {
            baseSolrUrl = liveNodes.iterator().next();
        }
        if (baseSolrUrl != null) {
            baseSolrUrl = baseSolrUrl.replaceAll("_", "/");
        }
        return baseSolrUrl;
    }

    /**
     * 获取维护索引用SolrClient
     *
     * @return
     */
    public ConcurrentUpdateSolrClient getUpdateClient(String collection) throws Exception {
        int cussThreadCount = 2;
        int cussQueueSize = 10;
        String baseSolrUrl = getBaseSolrUrl();
        ConcurrentUpdateSolrClient client
                = (new ConcurrentUpdateSolrClient.Builder("http://" + baseSolrUrl + "/" + collection))
                .withQueueSize(cussQueueSize)
                .withThreadCount(cussThreadCount).build();
        return client;
    }
}
