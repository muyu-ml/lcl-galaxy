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

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DataGetTest {
    @Autowired
    private RestHighLevelClient restHighLevelClient;
    
    private final String INDEX = "demoindex123";
    private final String TYPE = "demo123";

    @Test
    public void getIncludeData() throws Exception{
        GetRequest request = new GetRequest(INDEX, TYPE, "11111");
        String[] includes = new String[]{"content", "修改"};
        String[] excludes = Strings.EMPTY_ARRAY;
        FetchSourceContext fetchSourceContext = new FetchSourceContext(true, includes, excludes);
        request.fetchSourceContext(fetchSourceContext);
        this.getData(request);
    }

    private void getData(GetRequest request) throws Exception{
        GetResponse response = restHighLevelClient.get(request);
        log.info("response====================={}", new Gson().toJson(response));
        Map<String, Object> source = response.getSource();
        log.info("source====================={}", new Gson().toJson(source));
    }
}

