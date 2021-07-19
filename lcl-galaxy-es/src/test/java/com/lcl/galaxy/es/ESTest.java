package com.lcl.galaxy.es;

import com.google.gson.Gson;
import com.lcl.galaxy.es.dto.SkuInfo;
import com.lcl.galaxy.es.service.SkuService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ESTest {
    @Autowired
    private SkuService skuService;

    @Test
    public void testSave(){
        SkuInfo skuInfo = new SkuInfo();
        skuInfo.setSkuId("1501009001");
        skuInfo.setName("原味切片面包（10片装）");
        skuInfo.setCategory("101");
        skuInfo.setPrice(880);
        skuInfo.setBrand("良品铺子");
        SkuInfo skuInfo1 = skuService.save(skuInfo);

        skuInfo = new SkuInfo();
        skuInfo.setSkuId("1501009002");
        skuInfo.setName("原味切片面包（6片装）");
        skuInfo.setCategory("101");
        skuInfo.setPrice(680);
        skuInfo.setBrand("良品铺子");
        SkuInfo skuInfo2 = skuService.save(skuInfo);

        skuInfo = new SkuInfo();
        skuInfo.setSkuId("1501009004");
        skuInfo.setName("元气吐司850g");
        skuInfo.setCategory("101");
        skuInfo.setPrice(120);
        skuInfo.setBrand("百草味");
        SkuInfo skuInfo3 = skuService.save(skuInfo);
        log.info("save test========={},======={},========{}",new Gson().toJson(skuInfo1),new Gson().toJson(skuInfo2),new Gson().toJson(skuInfo3));
    }

    @Test
    public void testCount() {
        log.info("testcount ========== {}", skuService.count());
    }


    @Test
    public void testDelete() {
        SkuInfo skuInfo = new SkuInfo();
        skuInfo.setSkuId("1501009002");
        skuService.delete(skuInfo);
        log.info("delete test finish");
    }

    @Test
    public void testGetAll() {
        Iterable<SkuInfo> iterable = skuService.getAll();
        iterable.forEach(e->log.info("find all test ====== {}", new Gson().toJson(e)));
    }

    @Test
    public void testGetByName() {
        List<SkuInfo> list = skuService.getByName("面包");
        log.info("testGetByName test ====== {}", new Gson().toJson(list));
    }

    @Test
    public void testPage() {
        Page<SkuInfo> page = skuService.pageQuery(0, 10, "切片");
        log.info("Page test ====== {}======{}======{}", page.getTotalPages(), page.getNumber(), page.getContent());
    }
}

