package com.lcl.galaxy.mybatis.frame.sqlsource;

import com.lcl.galaxy.mybatis.frame.util.GenericTokenParser;
import com.lcl.galaxy.mybatis.frame.util.ParameterMappingTokenHandler;
import com.lcl.galaxy.mybatis.frame.util.TokenHandler;

public class MySqlSourceParser {
    public MySqlSource parse(String sqlText) {
        ParameterMappingTokenHandler tokenHandler = new ParameterMappingTokenHandler();
        GenericTokenParser tokenParser = new GenericTokenParser("#{","}" ,tokenHandler);
        String sql = tokenParser.parse(sqlText);
        return new MyStaticSqlSource(sql, tokenHandler.getParameterMappings());
    }
}
