package com.lcl.galaxy.es;

import com.google.gson.Gson;
import com.lcl.galaxy.es.dto.OrderInfo;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.ShardSearchFailure;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.search.MultiMatchQuery;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.ScoreSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DataSearchTest {
    @Autowired
    private RestHighLevelClient restHighLevelClient;

    private final String INDEX = "demoindex123";
    private final String TYPE = "demo123";
    private HighlightBuilder highlightBuilder;


    @Test
    public void searchAll() throws Exception{
        SearchRequest request = new SearchRequest(INDEX);
        request.types(TYPE);
        //SearchRequest request = new SearchRequest();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        request.source(searchSourceBuilder);
        this.search(request);
    }

    @Test
    public void searchPage() throws Exception{
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.termQuery("orderId", "5678"));
        //sourceBuilder.from(2);
        //sourceBuilder.size(5);
        SearchRequest request = new SearchRequest(INDEX);
        request.source(sourceBuilder);
        this.search(request);
    }


    @Test
    public void matchQuery() throws Exception{
        /*QueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("orderId", "5678")
                .fuzziness(Fuzziness.AUTO)
                .prefixLength(3)
                .maxExpansions(10);*/
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //searchSourceBuilder.query(matchQueryBuilder);
        //排序
        //searchSourceBuilder.sort(new ScoreSortBuilder().order(SortOrder.DESC));
        searchSourceBuilder.sort(new FieldSortBuilder("orderId").order(SortOrder.ASC));

        //过滤字段
        String[] includeFields = new String[] {"orderId", "orderName", "content"};
        String[] excludeFields = new String[] {"_type"};
        searchSourceBuilder.fetchSource(includeFields, excludeFields);

        //添加高亮
        highlightBuilder = new HighlightBuilder();
        HighlightBuilder.Field highligthName = new HighlightBuilder.Field("orderName"); //设置高亮字段
        highligthName.highlighterType("unified");
        highlightBuilder.field(highligthName);

        HighlightBuilder.Field highligthContent = new HighlightBuilder.Field("content"); //设置高亮字段
        highligthName.highlighterType("unified");
        highlightBuilder.field(highligthContent);
        SearchRequest request = new SearchRequest(INDEX);
        request.types(TYPE);
        request.source(searchSourceBuilder);
        this.search(request);
    }



    private void search(SearchRequest request) throws Exception{
        SearchResponse response = restHighLevelClient.search(request);
        log.info("response====================={}", new Gson().toJson(response));
        //关于请求执行本身的有用信息，如HTTP状态代码、执行时间或请求是否提前终止或超时：
        RestStatus status = response.status();
        TimeValue took = response.getTook();
        Boolean terminatedEarly = response.isTerminatedEarly();
        boolean timedOut = response.isTimedOut();
        log.info("status:{}=======took:{}====terminatedEarly:{}=========timedOut:{}",status,took,terminatedEarly,timedOut);
        //有关碎片级执行的信息，提供了有关受搜索影响的碎片总数以及成功碎片和不成功碎片的统计信息
        int totalShards = response.getTotalShards();
        int successfulShards = response.getSuccessfulShards();
        int failedShards = response.getFailedShards();
        log.info("totalShards:{}=======successfulShards:{}====failedShards:{}",totalShards,successfulShards,failedShards);
        for (ShardSearchFailure failure : response.getShardFailures()) {
            log.info("failure==============={}", new Gson().toJson(failure));
        }
        //将返回的数据返回到SearchHits对象中
        SearchHits hits = response.getHits();
        //SearchHits包含了总点击次数、最大得分等信息：
        long totalHits = hits.getTotalHits();
        float maxScore = hits.getMaxScore();
        log.info("totalHits:{}=======maxScore:{}",totalHits,maxScore);
        //对SearchHits进行遍历得到单个的SearchHit对象，SearchHit包含索引、类型、数据id和数据的得分情况
        SearchHit[] searchHits = hits.getHits();
        for (SearchHit hit : searchHits) {
            String index = hit.getIndex();
            String type = hit.getType();
            String id = hit.getId();
            float score = hit.getScore();
            log.info("index:{}=======type:{}=======id:{}=======score:{}",index,type,id,score);
            //可以将数据JSON字符串或键/值对映射的形式返回。
            String sourceAsString = hit.getSourceAsString();
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            String documentTitle = (String) sourceAsMap.get("title");
            List<Object> users = (List<Object>) sourceAsMap.get("name");
            Map<String, Object> innerObject = (Map<String, Object>) sourceAsMap.get("innerObject");
            log.info("sourceAsString:{}=======documentTitle:{}=======users:{}",sourceAsString,documentTitle,new Gson().toJson(users));
            //设置高亮
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            //获取title高亮显示
            HighlightField highlight = highlightFields.get("title");
            if(highlight != null){
                //获取高亮显示的字段
                Text[] fragments = highlight.fragments();
                String fragmentString = fragments[0].string();
                log.info("fragments:{}=======fragmentString:{}",fragments,fragmentString);
            }
        }



    }


}

