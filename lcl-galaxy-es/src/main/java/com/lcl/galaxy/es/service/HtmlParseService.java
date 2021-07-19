package com.lcl.galaxy.es.service;

import com.lcl.galaxy.es.dto.HtmlParseDTO;
import com.lcl.galaxy.es.utils.HtmlParseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HtmlParseService {
    /*@Autowired
    private RestHighLevelClient restHighLevelClient;
    @Autowired
    private HtmlParseUtil htmlParseUtil;

    //1.解析数据放入es索引中
    public Boolean parseHtmlParseDTO(String keywords) throws Exception{
        List<HtmlParseDTO> HtmlParseDTOs = htmlParseUtil.parseJD(keywords);
        //把查询出来的数据放入es里面
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout("2m");

        for(int i=0;i<HtmlParseDTOs.size();i++){
            System.out.println(HtmlParseDTOs.get(i));
            bulkRequest.add(
                    new IndexRequest("jd_goods_2",keywords+"")
                            .source(JSON.toJSONString(HtmlParseDTOs.get(i)), XHtmlParseDTOType.JSON));
        }
        BulkResponse bulk = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        return !bulk.hasFailures();
    }

    //2.获取这些数据实现搜索功能
    public List<Map<String,Object>> searchPage(String keyword, int pageNo, int pageSize) throws IOException {
        if(pageNo<=1){
            pageNo = 1;
        }
        //条件搜索
        SearchRequest searchRequest = new SearchRequest("jd_goods");
        //资源构造器(封装查询条件)
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        //分页
        sourceBuilder.from(pageNo);
        sourceBuilder.size(pageSize);

        //精准匹配
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("title", keyword);
        sourceBuilder.query(termQueryBuilder);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        //执行搜索
        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        //解析结果
        List<Map<String,Object>> list = new ArrayList<>();
        for(SearchHit documentFields:searchResponse.getHits().getHits()){
            list.add(documentFields.getSourceAsMap());
        }
        return list;
    }*/

}
