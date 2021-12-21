package com.lcl.dubbo.service;


import com.lcl.dubbo.bean.Employee;

/**
 * company: www.kaikeba.com
 * Author: Rey
 */
public interface EmployeeService {
    void addEmployee(Employee employee);

    Employee findEmployeeById(int id);

    Integer findEmployeeCount();
}
