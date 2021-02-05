package com.lcl.galaxy.lcl.galaxy.mongo.service;

import com.alibaba.fastjson.JSON;
import com.lcl.galaxy.lcl.galaxy.mongo.doamin.UserDo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class MongoService2 {

    private final UserRepository userRepository;

    /*public void maintest(){
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
        userRepository.insert(UserDo.builder().name("lcl").city("bj").age(18).build());
        userRepository.insert(UserDo.builder().name("1mm").city("ly").age(15).build());
        userRepository.insert(UserDo.builder().name("zbx").city("zz").age(22).build());
        userRepository.insert(UserDo.builder().name("lcl1").city("bj1").age(181).build());
        userRepository.insert(UserDo.builder().name("1mm1").city("ly1").age(151).build());
        userRepository.insert(UserDo.builder().name("zbx1").city("zz1").age(221).build());
        userRepository.insert(UserDo.builder().name("lcl1").city("bj1").age(181).build());
        userRepository.insert(UserDo.builder().name("1mm1").city("ly1").age(151).build());
        userRepository.insert(UserDo.builder().name("zbx1").city("zz1").age(221).build());
        log.info("插入文档成功");
        List<UserDo> userDoList = userRepository.findAll();
        for (UserDo userDo : userDoList){
            log.info("插入后的结果：【{}】", JSON.toJSONString(userDo));
        }
    }

    public void findDocument(){
        List<UserDo> userDoList = userRepository.findAll();
        for (UserDo userDo : userDoList){
            log.info("文档循环结果：【{}】", JSON.toJSONString(userDo));
        }
    }

    public void updateDocument(){

        ExampleMatcher matcher = ExampleMatcher.matching().withIgnorePaths("name","age","city");
        Example<UserDo> example = Example.of(UserDo.builder().name("lcl").build(), matcher);
        List<UserDo> userDoList = userRepository.findAll(example);
        for (UserDo userDo : userDoList){
            userDo.setCity("beijing");
            userRepository.
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
    }*/


}
