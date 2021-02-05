package com.lcl.galaxy.lcl.galaxy.mongo;

import com.mongodb.BasicDBObject;
/*import com.mongodb.MongoClient;*/
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.Filters;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
/*import org.junit.Before;*/
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

@SpringBootTest
@Slf4j
class LclGalaxyMongoApplicationTests {

    @Test
    void contextLoads() {
    }


    /*private MongoClient client;

    @Before
    public void init(){
        client = new MongoClient("8.131.245.53",27017);
    }

    @Test
    public void createConnection(){
        MongoDatabase mongoDatabase = client.getDatabase("lcltest5");
        mongoDatabase.createCollection("mycollection");
        log.info("集合创建成功");
    }

    public void getCollections(){
        MongoDatabase mongoDatabase = client.getDatabase("lcltest5");
        MongoCollection<Document> mycollection = mongoDatabase.getCollection("mycollection");
        log.info("获取集合成功：{}",mycollection.getNamespace());
        MongoIterable<String> names = mongoDatabase.listCollectionNames();
        for (String name : names){
            log.info("循环集合名称：{}",name);
        }
    }

    public void insertDocument(){
        MongoDatabase mongoDatabase = client.getDatabase("lcltest5");
        MongoCollection<Document> mycollection = mongoDatabase.getCollection("mycollection");
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
        List<Document> documentList = Arrays.asList(document1,document2,document3,document4);
        mycollection.insertMany(documentList);
        log.info("插入文档成功");
        FindIterable<Document> documents = mycollection.find();
        for (Document document : documents){
            log.info("插入后的结果：【{}】", document.toJson());
        }
    }

    public void findDocument(){
        MongoDatabase mongoDatabase = client.getDatabase("lcltest5");
        MongoCollection<Document> mycollection = mongoDatabase.getCollection("mycollection");
        FindIterable<Document> documents = mycollection.find();
        for (Document document : documents){
            log.info("文档循环结果：【{}】", document.toJson());
        }
    }

    public void updateDocument(){
        MongoDatabase mongoDatabase = client.getDatabase("lcltest5");
        MongoCollection<Document> mycollection = mongoDatabase.getCollection("mycollection");
        mycollection.updateMany(Filters.eq("name","lcl"), new Document("age",31));
        FindIterable<Document> documents = mycollection.find();
        for (Document document : documents){
            log.info("更新后的结果：【{}】", document.toJson());
        }
    }

    public void deleteDocument(){
        MongoDatabase mongoDatabase = client.getDatabase("lcltest5");
        MongoCollection<Document> mycollection = mongoDatabase.getCollection("mycollection");
        mycollection.deleteMany(Filters.eq("city","jx"));
        FindIterable<Document> documents = mycollection.find();
        for (Document document : documents){
            log.info("删除后的结果：【{}】", document.toJson());
        }
    }

    public void query(){
        MongoDatabase mongoDatabase = client.getDatabase("lcltest5");
        MongoCollection<Document> mycollection = mongoDatabase.getCollection("mycollection");
        Document first = mycollection.find().first();
        log.info("查询第一条的city：{}",first.get("city"));
        //查询指定字段
        Document document = new Document().append("_id",0).append("city",0).append("name",1);
        FindIterable<Document> projection = mycollection.find().projection(document);
        for (Document document1 : projection){
            log.info("按条件输出结果：【{}】", document1.toJson());
        }
    }

    public void query1(){
        MongoDatabase mongoDatabase = client.getDatabase("lcltest5");
        MongoCollection<Document> mycollection = mongoDatabase.getCollection("mycollection");
        //查询指定字段
        Document document = new Document().append("age",-1);
        FindIterable<Document> projection = mycollection.find().sort(document);
        for (Document document1 : projection){
            log.info("排序输出结果：【{}】", document1.toJson());
        }
    }

    public void likequery(){
        MongoDatabase mongoDatabase = client.getDatabase("lcltest5");
        MongoCollection<Document> mycollection = mongoDatabase.getCollection("mycollection");
        Pattern pattern = Pattern.compile("^.*l.*$", Pattern.CASE_INSENSITIVE);
        BasicDBObject query = new BasicDBObject("name",pattern);
        FindIterable<Document> projection = mycollection.find(query);
        for (Document document1 : projection){
            log.info("模糊查询输出结果：【{}】", document1.toJson());
        }
    }

    public void page(){
        MongoDatabase mongoDatabase = client.getDatabase("lcltest5");
        MongoCollection<Document> mycollection = mongoDatabase.getCollection("mycollection");
        Pattern pattern = Pattern.compile("^.*l.*$", Pattern.CASE_INSENSITIVE);
        BasicDBObject query = new BasicDBObject("name",pattern);
        Document document = new Document().append("age",-1);
        FindIterable<Document> projection = mycollection.find(query).sort(document).skip(2).limit(3);
        for (Document document1 : projection){
            log.info("分页查询输出结果：【{}】", document1.toJson());
        }
    }*/

}
