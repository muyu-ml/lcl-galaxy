package com.lcl.galaxy.es.service;

import com.lcl.galaxy.es.dto.SkuInfo;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

public interface SkuService {
    long count();

    SkuInfo save(SkuInfo skuInfo);

    void delete(SkuInfo skuInfo);

    Iterable<SkuInfo> getAll();

    List<SkuInfo> getByName(String name);

    Page<SkuInfo> pageQuery(Integer pageNo, Integer pageSize, String kw);
}
