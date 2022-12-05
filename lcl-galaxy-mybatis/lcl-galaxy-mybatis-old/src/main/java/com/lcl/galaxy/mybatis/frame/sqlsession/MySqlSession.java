package com.lcl.galaxy.mybatis.frame.sqlsession;

import java.util.List;

public interface MySqlSession {
    <T> T selectOne(String statementId, Object param);
    <T> List<T>  selctList(String statementId, Object param);
}
