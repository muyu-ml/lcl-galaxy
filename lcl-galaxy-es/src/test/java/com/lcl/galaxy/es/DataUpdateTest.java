package com.lcl.galaxy.es;

import com.google.gson.Gson;
import com.lcl.galaxy.es.dto.OrderInfo;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.Strings;
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
public class DataUpdateTest {
    @Autowired
    private RestHighLevelClient restHighLevelClient;
    
    private final String INDEX = "demoindex123";
    private final String TYPE = "demo123";

    @Test
    public void updateByMapping() throws Exception{
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("orderId", 11111);
        jsonMap.put("orderName", "修改========1111的订单");
        jsonMap.put("content", "修改==============破欸俗人伟大撒打发的");
        UpdateRequest request = new UpdateRequest(INDEX,TYPE, jsonMap.get("orderId").toString()).doc(jsonMap);
        this.update(request);
    }


    private boolean update(UpdateRequest request) throws Exception{
        UpdateResponse response = restHighLevelClient.update(request);
        RestStatus restStatus = response.status();
        log.info("response====================={}", new Gson().toJson(response));
        if (response.status().getStatus() == 200) {
            return true;
        }
        return false;
    }


}

