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
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.Strings;
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
public class DataSaveTest {
    @Autowired
    private RestHighLevelClient restHighLevelClient;
    
    private final String INDEX = "demoindex123";
    private final String TYPE = "demo123";

    @Test
    public void saveDataByJson() throws Exception{
        OrderInfo orderInfo = OrderInfo.builder().orderId(12345).orderName("测试12345的订单").content("的撒出手大方【平时都i法第四u发").build();
        IndexRequest request = new IndexRequest(INDEX, TYPE, String.valueOf(orderInfo.getOrderId()));
        request.source(new Gson().toJson(orderInfo), XContentType.JSON);
        this.save(request);
    }

    @Test
    public void saveDataBMap() throws Exception{
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("orderId", 11111);
        jsonMap.put("orderName", "1111的订单");
        jsonMap.put("content", "破欸俗人伟大撒打发的");
        IndexRequest request = new IndexRequest(INDEX,TYPE, jsonMap.get("orderId").toString()).source(jsonMap);
        this.save(request);
    }

    @Test
    public void saveDataByContentBuild() throws Exception{
        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder.startObject();
        builder.field("orderId",2222);
        builder.field("orderName", "2222的订单");
        builder.field("content", "送iu恶意然后我i和拉票；萨拉宽度");
        builder.endObject();
        IndexRequest request = new IndexRequest(INDEX, TYPE, "2222").source(builder);
        this.save(request);
    }

    @Test
    public void saveDataByObject() throws Exception{
        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder.startObject();
        builder.field("orderId",2222);
        builder.field("orderName", "2222的订单");
        builder.field("content", "送iu恶意然后我i和拉票；萨拉宽度");
        builder.endObject();
        IndexRequest request = new IndexRequest(INDEX, TYPE, "3333").source(
                "orderId",3333,"orderName", "3333的订单","content", "laisuyeiluqwgkjhgkjsgd"
        );
        this.save(request);
    }
    private boolean save(IndexRequest request) throws Exception{
        IndexResponse response = restHighLevelClient.index(request);
        log.info("response====================={}", new Gson().toJson(response));
        if (response.status().getStatus() == 200) {
            return true;
        }
        return false;
    }



}

