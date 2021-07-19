package com.lcl.galaxy.es.service.impl;

import com.lcl.galaxy.es.dao.SkuRepository;
import com.lcl.galaxy.es.dto.SkuInfo;
import com.lcl.galaxy.es.service.SkuService;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SkuServiceImpl implements SkuService{

    @Autowired
    private SkuRepository skuRepository;

    @Override
    public long count() {
        return skuRepository.count();
    }

    @Override
    public SkuInfo save(SkuInfo skuInfo) {
        return skuRepository.save(skuInfo);
    }

    @Override
    public void delete(SkuInfo skuInfo) {
        skuRepository.delete(skuInfo);
        //skuRepository.deleteById(skuInfo.getSkuId());
    }

    @Override
    public Iterable<SkuInfo> getAll() {
        return skuRepository.findAll();
    }

    @Override
    public List<SkuInfo> getByName(String name) {
        List<SkuInfo> list = new ArrayList<>();
        MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder("name", name);
        Iterable<SkuInfo> iterable = skuRepository.search(matchQueryBuilder);
        iterable.forEach(e -> list.add(e));
        return list;
    }

    @Override
    public Page<SkuInfo> pageQuery(Integer pageNo, Integer pageSize, String kw) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchPhrasePrefixQuery("name", kw))
                .withPageable(PageRequest.of(pageNo, pageSize)).build();
        return skuRepository.search(searchQuery);
    }
}
