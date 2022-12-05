package com.lcl.galaxy.mybatis.frame.util;


import com.lcl.galaxy.mybatis.frame.sqlsource.MyParameterMapping;

import java.util.ArrayList;
import java.util.List;

public class ParameterMappingTokenHandler implements TokenHandler {
	private List<MyParameterMapping> parameterMappings = new ArrayList<>();

	// context是参数名称
	@Override
	public String handleToken(String content) {
		parameterMappings.add(buildParameterMapping(content));
		return "?";
	}

	private MyParameterMapping buildParameterMapping(String content) {
		MyParameterMapping parameterMapping = new MyParameterMapping(content);
		return parameterMapping;
	}

	public List<MyParameterMapping> getParameterMappings() {
		return parameterMappings;
	}

	public void setParameterMappings(List<MyParameterMapping> parameterMappings) {
		this.parameterMappings = parameterMappings;
	}

}
