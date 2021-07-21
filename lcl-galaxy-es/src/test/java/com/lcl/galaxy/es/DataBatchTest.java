package com.lcl.galaxy.es;

import com.google.gson.Gson;
import com.lcl.galaxy.es.dto.OrderInfo;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.rest.RestStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DataBatchTest {
    @Autowired
    private RestHighLevelClient restHighLevelClient;

    private final String INDEX = "demoindex123";
    private final String TYPE = "demo123";



    @Test
    public void batch() throws Exception{
        BulkRequest request = new BulkRequest();
        request.add(new IndexRequest(INDEX, TYPE, "5678").source(
                new Gson().toJson(OrderInfo.builder().orderId(5678).orderName("5678的订单").content("我饿请问一日").build()),
                XContentType.JSON));
        request.add(new IndexRequest(INDEX, TYPE, "56781").source(
                new Gson().toJson(OrderInfo.builder().orderId(56781).orderName("56781的订单").content("我饿请问一日").build()),
                XContentType.JSON));
        request.add(new IndexRequest(INDEX, TYPE, "56782").source(
                new Gson().toJson(OrderInfo.builder().orderId(56782).orderName("56782的订单").content("我饿请问一日").build()),
                XContentType.JSON));
        request.add(new IndexRequest(INDEX, TYPE, "56783").source(
                new Gson().toJson(OrderInfo.builder().orderId(56783).orderName("56783的订单").content("我饿请问一日").build()),
                XContentType.JSON));

        BulkResponse response = restHighLevelClient.bulk(request);
        log.info("response====================={}", new Gson().toJson(response));
    }
}

