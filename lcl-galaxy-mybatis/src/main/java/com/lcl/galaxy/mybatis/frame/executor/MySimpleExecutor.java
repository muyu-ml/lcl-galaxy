package com.lcl.galaxy.mybatis.frame.executor;

import com.lcl.galaxy.mybatis.frame.config.MyConfiguration;
import com.lcl.galaxy.mybatis.frame.config.MyMappedStatement;
import com.lcl.galaxy.mybatis.frame.sqlsource.MyBoundSql;
import com.lcl.galaxy.mybatis.frame.sqlsource.MyParameterMapping;
import com.lcl.galaxy.mybatis.frame.sqlsource.MySqlSource;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

@Slf4j
public class MySimpleExecutor extends MyBaseExecutor{
    @Override
    protected List<Object> queryFromDataBase(MyMappedStatement myMappedStatement, MyConfiguration myConfiguration, Object param) {
        List<Object> resultList = new ArrayList<>();
        try {
            Connection connection = getConnection(myConfiguration);
            MyBoundSql myBoundSql = getBoundSql(myMappedStatement.getMySqlSource(), param);
            if ("PREPARED".equals(myMappedStatement.getStatementType().toUpperCase())){
                PreparedStatement preparedStatement = createStatement(connection, myBoundSql);
                handlerParamter(preparedStatement, myBoundSql, param);
                log.info("sql语句：{}", myBoundSql.getSql());
                ResultSet resultSet = preparedStatement.executeQuery();
                handleResult(resultList, myMappedStatement, resultSet);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return resultList;
    }

    private void handleResult(List<Object> resultList, MyMappedStatement myMappedStatement, ResultSet rs) throws Exception {
        Class<?> resultType = myMappedStatement.getResultType();
        while (rs.next()){
            Object object = resultType.newInstance();
            ResultSetMetaData rsMetaData = rs.getMetaData();
            int columnCount = rsMetaData.getColumnCount();
            for (int i = 0; i< columnCount; i++){
                String columName = rsMetaData.getColumnName(i+1);
                Field declaredField = resultType.getDeclaredField(columName);
                declaredField.setAccessible(true);
                declaredField.set(object, rs.getObject(i + 1));
            }
            resultList.add(object);
        }
    }

    private void handlerParamter(PreparedStatement preparedStatement, MyBoundSql myBoundSql, Object param) throws Exception {
        if(param instanceof Integer){
            preparedStatement.setInt(1, (Integer) param);
        }else if(param instanceof String){
            preparedStatement.setString(1, String.valueOf(param));
        }else {
            List<MyParameterMapping> myParameterMappings = myBoundSql.getMyParameterMappings();
            for (int i=0; i< myParameterMappings.size(); i++){
                MyParameterMapping myParameterMapping = myParameterMappings.get(i);
                String name = myParameterMapping.getName();
                Class<?> clazz = param.getClass();
                Field field = clazz.getDeclaredField(name);
                field.setAccessible(true);
                Object object = field.get(param);
                preparedStatement.setObject(i+1, object);
            }
        }
    }

    private PreparedStatement createStatement(Connection connection, MyBoundSql myBoundSql) throws Exception {
        PreparedStatement preparedStatement = connection.prepareStatement(myBoundSql.getSql());
        return preparedStatement;
    }

    private MyBoundSql getBoundSql(MySqlSource mySqlSource, Object param) {
        MyBoundSql boundSql = mySqlSource.getBoundSql(param);
        return boundSql;
    }

    private Connection getConnection(MyConfiguration myConfiguration) throws SQLException {
        DataSource dataSource = myConfiguration.getDataSource();
        Connection connection = dataSource.getConnection();
        return connection;
    }
}
