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
public class ESTest3 {
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

    @Test
    public void getIncludeData() throws Exception{
        GetRequest request = new GetRequest(INDEX, TYPE, "11111");
        String[] includes = Strings.EMPTY_ARRAY;//new String[]{"content", "修改"};
        String[] excludes = Strings.EMPTY_ARRAY;
        FetchSourceContext fetchSourceContext = new FetchSourceContext(true, includes, excludes);
        request.fetchSourceContext(fetchSourceContext);
        this.getData(request);
    }

    private void getData(GetRequest request) throws Exception{
        //https://zhuanlan.zhihu.com/p/143786937
        GetResponse response = restHighLevelClient.get(request);
        log.info("response====================={}", new Gson().toJson(response));
        Map<String, Object> source = response.getSource();
        log.info("source====================={}", new Gson().toJson(source));
    }

    @Test
    public void deleteData() throws Exception{
        DeleteRequest request = new DeleteRequest(INDEX,TYPE,"2222");
        DeleteResponse response = restHighLevelClient.delete(request);
        RestStatus restStatus = response.status();
    }

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

