package com.lcl.galaxy.pattern.creative.build.director;

import com.lcl.galaxy.pattern.creative.build.builder.StudentBuilder;
import com.lcl.galaxy.pattern.creative.build.product.Student;

// 导演类/测试类
public class BuildDemo {

	public static void main(String[] args) {
		
		StudentBuilder builder = new StudentBuilder();
		// 决定如何创建一个Student
		Student student = builder.age(1).name("zhangsan").father("zhaosi").build();
		
		//builder.build(xxxxxxxx).build();
		System.out.println(student);

	}
}
