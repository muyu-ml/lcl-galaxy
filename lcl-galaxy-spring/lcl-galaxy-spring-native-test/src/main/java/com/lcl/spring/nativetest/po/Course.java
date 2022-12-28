package com.lcl.spring.nativetest.po;

import lombok.Data;

/**
 * 课程类 拥有初始化方法
 * 
 * @author think
 *
 */
@Data
public class Course {

	private String name;

	private Integer age;

	public void init() {
		System.out.println("我是对象初始化方法");
	}

	@Override
	public String toString() {
		return "Course [name=" + name + ", age=" + age + "]";
	}

}
