package com.lcl.galaxy.es.config;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;

public class CommonRequest {

    public CreateIndexRequest CreateIndexRequest(String index, String json){
        return createIndexRequest(index, 5, 1, "docdemo", json, XContentType.JSON);
    }

    public CreateIndexRequest createIndexRequest(String index, int shardNum, int replicasNum, String type, String json, XContentType xContentType){
        CreateIndexRequest request = new CreateIndexRequest(index);
        request.settings(Settings.builder().put("index.number_of_shards", shardNum).put("index.number_of_replicas", replicasNum).build());
        request.mapping(type, json, xContentType);
        return request;
    }
}
