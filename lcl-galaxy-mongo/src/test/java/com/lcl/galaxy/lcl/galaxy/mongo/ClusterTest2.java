package com.lcl.galaxy.lcl.galaxy.mongo;

import com.lcl.galaxy.lcl.galaxy.mongo.doamin.Employee;
import com.lcl.galaxy.lcl.galaxy.mongo.repository.EmployeeRepository;
import com.mongodb.client.result.UpdateResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.query.UpdateDefinition;

import java.util.List;


@SpringBootTest
public class ClusterTest2 {

    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    public void add() {
        Employee employee = Employee.builder()
                .id("11").firstName("liu").lastName("hero").empId(1).salary(10200).build();
        employeeRepository.save(employee);
    }

    @Test
    public void findAll() {
        List<Employee> employees = employeeRepository.findAll();
        employees.forEach(System.out::println);
    }
}
