package com.lcl.mybatisnative.config;

import com.lcl.mybatisnative.sqlsource.MySqlSource;
import org.dom4j.Element;

public class MyXmlStatementParser {

    private static final String CONNECTOR = ".";

    private MyConfiguration myConfiguration;
    public MyXmlStatementParser(MyConfiguration myConfiguration) {
        this.myConfiguration = myConfiguration;
    }

    public void parse(Element element, String namespace) {
        String id = element.attributeValue("id");
        String statementId = namespace + CONNECTOR + id;
        String parameterType = element.attributeValue("parameterType");
        String resultType = element.attributeValue("resultType");
        Class<?> parameterTypeClass = resolveClass(parameterType);
        Class<?> resultTypeClass = resolveClass(resultType);
        String statementType = element.attributeValue("statementType");
        statementType = statementType == null || statementType.equals("")? "PREPARED":statementType;
        MySqlSource mySqlSource = createMySqlSource(element);
        MyMappedStatement mappedStatement = new MyMappedStatement(statementId, parameterTypeClass, resultTypeClass, statementType, mySqlSource);
        myConfiguration.getMappedStatementMap().put(statementId,mappedStatement);
    }

    private MySqlSource createMySqlSource(Element element) {
        MyXmlScriptParser myXmlScriptParser = new MyXmlScriptParser(myConfiguration);
        return myXmlScriptParser.parseScriptNode(element);
    }

    private Class<?> resolveClass(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
