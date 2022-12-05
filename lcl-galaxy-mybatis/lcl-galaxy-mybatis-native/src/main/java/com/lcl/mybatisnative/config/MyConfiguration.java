package com.lcl.mybatisnative.config;

import com.lcl.mybatisnative.sqlsession.MySqlSession;
import lombok.Data;
import org.apache.ibatis.binding.BindingException;
import org.apache.ibatis.binding.MapperProxyFactory;
import org.apache.ibatis.session.SqlSession;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Data
public class MyConfiguration {
    private DataSource dataSource;
    private Map<String, MyMappedStatement> mappedStatementMap = new HashMap<>();

    public MyMappedStatement getMyMappedStatement(String statementId){
        return mappedStatementMap.get(statementId);
    }

    public void SetMyMappedStatement(String statementId, MyMappedStatement mappedStatement){
        mappedStatementMap.put(statementId, mappedStatement);
    }

}
