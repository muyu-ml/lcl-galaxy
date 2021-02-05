package com.lcl.galaxy.lcl.galaxy.mongo.service;

import com.lcl.galaxy.lcl.galaxy.mongo.doamin.UserDo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<UserDo, String> {

}
