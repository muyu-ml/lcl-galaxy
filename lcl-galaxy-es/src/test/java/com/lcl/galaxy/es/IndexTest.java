package com.lcl.galaxy.es;

import com.google.gson.Gson;
import com.lcl.galaxy.es.dto.OrderInfo;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.Strings;
import org.elasticsearch.common.document.DocumentField;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class IndexTest {
    @Autowired
    private RestHighLevelClient restHighLevelClient;
    
    private final String INDEX = "demoindex123";
    private final String TYPE = "demo123";

    @Test
    public void creatIndex() throws Exception{

        Map<String, Object> properties = new HashMap<>();
        Map<String, Object> orderIdIndex = new HashMap<>();
        orderIdIndex.put("type", "long");
        orderIdIndex.put("store", true);
        orderIdIndex.put("index", true);
        properties.put("orderId", orderIdIndex);

        Map<String, Object> orderNameIndex = new HashMap<>();
        orderNameIndex.put("type", "text");
        orderNameIndex.put("store", true);
        orderNameIndex.put("index", true);
        properties.put("orderName", orderNameIndex);

        Map<String, Object> content = new HashMap<>();
        content.put("type", "text");
        content.put("store", true);
        content.put("index", true);
        properties.put("content", content);

        Map<String, Object> mapping = new HashMap<>();
        mapping.put("properties", properties);

        CreateIndexRequest request = new CreateIndexRequest(INDEX);
        request.settings(Settings.builder().put("index.number_of_shards", 3).put("index.number_of_replicas", 2).build());
        request.mapping(TYPE, new Gson().toJson(mapping), XContentType.JSON);

        //request.mapping(mapping);
        CreateIndexResponse response = restHighLevelClient.indices().create(request);
        log.info("response====================={}", new Gson().toJson(response));
    }

    @Test
    public void deleteIndex() throws Exception{
        DeleteIndexRequest request = new DeleteIndexRequest(INDEX);
        DeleteIndexResponse response = restHighLevelClient.indices().delete(request);
        log.info("response====================={}", new Gson().toJson(response));
    }


}

