package com.lcl.galaxy.lcl.galaxy.mongo.service;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.Filters;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

@Service
@Slf4j
public class MongDBService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void createConnection(){
        MongoCollection<Document> mycollection = mongoTemplate.createCollection("lcltest5");
        log.info("集合创建成功");
    }

    public void getCollections(){
        MongoCollection<Document> mycollection = mongoTemplate.getCollection("lcltest5");
        log.info("获取集合成功：{}",mycollection.getNamespace());

        Set<String> collectionNames = mongoTemplate.getCollectionNames();
        for (String name : collectionNames){
            log.info("循环集合名称：{}",name);
        }
    }

    public void insertDocument(){
        MongoCollection<Document> mycollection = mongoTemplate.getCollection("lcltest5");
        Document document1 = new Document("name","lcl").append("age",18).append("city","bj");
        Document document2 = new Document("name","qmm").append("age",16).append("city","ly");
        Document document3 = new Document("name","zbx").append("age",27).append("city","zz");
        Document document4 = new Document("name","lmj").append("age",31).append("city","jx");

        Document document11 = new Document("name","lcl1").append("age",181).append("city","bj1");
        Document document21 = new Document("name","qmm1").append("age",161).append("city","ly1");
        Document document31 = new Document("name","zbx1").append("age",271).append("city","zz1");
        Document document41 = new Document("name","lmj1").append("age",311).append("city","jx1");

        Document document12 = new Document("name","lcl2").append("age",182).append("city","bj2");
        Document document22 = new Document("name","qmm2").append("age",162).append("city","ly2");
        Document document32 = new Document("name","zbx2").append("age",272).append("city","zz2");
        Document document42 = new Document("name","lmj2").append("age",312).append("city","jx2");

        Document document13 = new Document("name","lcl3").append("age",183).append("city","bj3");
        Document document23 = new Document("name","qmm3").append("age",163).append("city","ly3");
        Document document33 = new Document("name","zbx3").append("age",273).append("city","zz3");
        Document document43 = new Document("name","lmj3").append("age",313).append("city","jx3");
        List<Document> documentList = Arrays.asList(document1,document2,document3,document4,
                document11,document21,document31,document41,
                document12,document22,document32,document42);
        mycollection.insertMany(documentList);
        log.info("插入文档成功");
        FindIterable<Document> documents = mycollection.find();
        for (Document document : documents){
            log.info("插入后的结果：【{}】", document.toJson());
        }
    }

    public void findDocument(){
        MongoCollection<Document> mycollection = mongoTemplate.getCollection("lcltest5");
        FindIterable<Document> documents = mycollection.find();
        for (Document document : documents){
            log.info("文档循环结果：【{}】", document.toJson());
        }
    }

    public void updateDocument(){
        MongoCollection<Document> mycollection = mongoTemplate.getCollection("lcltest5");
        mycollection.updateMany(Filters.eq("name","lcl"), new Document("$set",new Document("age",31)));
        FindIterable<Document> documents = mycollection.find();
        for (Document document : documents){
            log.info("更新后的结果：【{}】", document.toJson());
        }
    }

    public void deleteDocument(){
        MongoCollection<Document> mycollection = mongoTemplate.getCollection("lcltest5");
        mycollection.deleteMany(Filters.eq("city","jx"));
        FindIterable<Document> documents = mycollection.find();
        for (Document document : documents){
            log.info("删除后的结果：【{}】", document.toJson());
        }
    }

    public void query(){
        MongoCollection<Document> mycollection = mongoTemplate.getCollection("lcltest5");
        Document first = mycollection.find().first();
        log.info("查询第一条的city：{}",first.get("city"));
        //查询指定字段
        Document document = new Document().append("_id",0).append("city",1).append("name",1);
        FindIterable<Document> projection = mycollection.find().projection(document);
        for (Document document1 : projection){
            log.info("按条件输出结果：【{}】", document1.toJson());
        }
    }

    public void query1(){
        MongoCollection<Document> mycollection = mongoTemplate.getCollection("lcltest5");
        //查询指定字段
        Document document = new Document().append("age",-1);
        FindIterable<Document> projection = mycollection.find().sort(document);
        for (Document document1 : projection){
            log.info("排序输出结果：【{}】", document1.toJson());
        }
    }

    public void likequery(){
        MongoCollection<Document> mycollection = mongoTemplate.getCollection("lcltest5");
        Pattern pattern = Pattern.compile("^.*l.*$", Pattern.CASE_INSENSITIVE);
        BasicDBObject query = new BasicDBObject("name",pattern);
        FindIterable<Document> projection = mycollection.find(query);
        for (Document document1 : projection){
            log.info("模糊查询输出结果：【{}】", document1.toJson());
        }
    }

    public void page(){
        MongoCollection<Document> mycollection = mongoTemplate.getCollection("lcltest5");
        Pattern pattern = Pattern.compile("^.*l.*$", Pattern.CASE_INSENSITIVE);
        BasicDBObject query = new BasicDBObject("name",pattern);
        Document document = new Document().append("age",-1);
        FindIterable<Document> projection = mycollection.find(query).sort(document).skip(2).limit(3);
        for (Document document1 : projection){
            log.info("分页查询输出结果：【{}】", document1.toJson());
        }
    }

    public void maintest(){
        createConnection();
        getCollections();
        insertDocument();
        findDocument();
        updateDocument();
        deleteDocument();
        query();
        query();
        likequery();
        page();
    }
}
