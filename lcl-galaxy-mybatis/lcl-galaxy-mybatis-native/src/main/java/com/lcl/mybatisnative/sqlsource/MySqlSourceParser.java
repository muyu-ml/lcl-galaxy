package com.lcl.mybatisnative.sqlsource;

import com.lcl.mybatisnative.util.GenericTokenParser;
import com.lcl.mybatisnative.util.ParameterMappingTokenHandler;

public class MySqlSourceParser {
    public MySqlSource parse(String sqlText) {
        ParameterMappingTokenHandler tokenHandler = new ParameterMappingTokenHandler();
        GenericTokenParser tokenParser = new GenericTokenParser("#{","}" ,tokenHandler);
        String sql = tokenParser.parse(sqlText);
        return new MyStaticSqlSource(sql, tokenHandler.getParameterMappings());
    }
}
