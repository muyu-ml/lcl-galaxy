package com.lcl.galaxy.es.dao;

import com.lcl.galaxy.es.dto.SkuInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkuRepository extends ElasticsearchRepository<SkuInfo, String> {

}
