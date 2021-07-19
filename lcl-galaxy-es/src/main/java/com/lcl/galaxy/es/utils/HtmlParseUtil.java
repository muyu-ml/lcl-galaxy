package com.lcl.galaxy.es.utils;

import com.lcl.galaxy.es.dto.HtmlParseDTO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;


@Component
public class HtmlParseUtil {
    public List<HtmlParseDTO> parseJD(String keywords)throws Exception{
        //1.获取请求
        String url = "https://search.jd.com/Search?keyword="+keywords;
        //2.解析网页(Jsoup返回Document就是浏览器的Document对象)
        Document document = Jsoup.parse(new URL(url), 30000);
        Element ele = document.getElementById("J_goodsList");
        //System.out.println(ele);
        List<HtmlParseDTO> list = new ArrayList<>();
        //获取所有的li标签
        Elements tag_lis = ele.getElementsByTag("li");
        //获取元素中的内容,这里每个element就是li标签
        for(Element element:tag_lis){
            String img = element.getElementsByTag("img").eq(0).attr("src");
            String price = element.getElementsByClass("p-price").eq(0).text();
            String title = element.getElementsByClass("p-name").eq(0).text();
            //封装对象
            HtmlParseDTO content = new HtmlParseDTO();
            content.setTitle(title);
            content.setImage(img);
            content.setPrice(price);
            list.add(content);
        }
        return list;
    }
}
