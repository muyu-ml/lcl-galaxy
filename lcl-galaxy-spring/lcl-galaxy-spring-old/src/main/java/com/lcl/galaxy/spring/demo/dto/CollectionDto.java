package com.lcl.galaxy.spring.demo.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

@Data
public class CollectionDto {

    private List<String> nameList;

    private Set<String> nameSet;

    private Map<String,String> nameMap;

    private Properties properties;

}
