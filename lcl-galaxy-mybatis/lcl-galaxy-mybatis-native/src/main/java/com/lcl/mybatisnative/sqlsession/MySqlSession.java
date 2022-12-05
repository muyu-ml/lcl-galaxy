package com.lcl.mybatisnative.sqlsession;

import java.util.List;

public interface MySqlSession {
    <T> T selectOne(String statementId, Object param);
    <T> List<T>  selectList(String statementId, Object param);
}
