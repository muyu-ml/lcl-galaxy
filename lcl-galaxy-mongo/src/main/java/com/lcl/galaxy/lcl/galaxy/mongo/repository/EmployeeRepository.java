package com.lcl.galaxy.lcl.galaxy.mongo.repository;


import com.lcl.galaxy.lcl.galaxy.mongo.doamin.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeeRepository extends MongoRepository<Employee, String> {}