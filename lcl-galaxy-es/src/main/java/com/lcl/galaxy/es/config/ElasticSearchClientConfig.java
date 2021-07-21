package com.lcl.galaxy.es.config;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.elasticsearch.client.RestHighLevelClient;

@Configuration
public class ElasticSearchClientConfig {
    @Value("${elasticsearch.clientIps}")
    private String clientIps;

    @Value("${elasticsearch.httpPort}")
    private int httpPort;

    @Value("${elasticsearch.username}")
    private String username;

    @Value("${elasticsearch.password}")
    private String password;

    private HttpHost[] getHttpHosts(String clientIps, int esHttpPort) {
        String[] clientIpList = clientIps.split(",");
        HttpHost[] httpHosts = new HttpHost[clientIpList.length];
        for (int i = 0; i < clientIpList.length; i++) {
            httpHosts[i] = new HttpHost(clientIpList[i], esHttpPort, "http");
        }
        return httpHosts;
    }

    /**
     * 创建带HTTP Basic Auth认证rest客户端
     */
    @Bean
    public RestHighLevelClient restHighLevelClient() {
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username, password));
        return new RestHighLevelClient(RestClient.builder(getHttpHosts(clientIps, httpPort)).setHttpClientConfigCallback((HttpAsyncClientBuilder httpAsyncClientBuilder) -> httpAsyncClientBuilder.setDefaultCredentialsProvider(credentialsProvider)));
    }

    //不带用户名密码验证
    //@Bean
    public RestHighLevelClient restClient() {
        return new RestHighLevelClient(RestClient.builder(getHttpHosts(clientIps, httpPort)));
    }
}
