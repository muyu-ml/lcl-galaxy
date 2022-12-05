package com.lcl.galaxy.mybatis.frame.config;

import com.lcl.galaxy.mybatis.frame.sqlnode.*;
import com.lcl.galaxy.mybatis.frame.sqlnode.handler.MyNodeHandler;
import com.lcl.galaxy.mybatis.frame.sqlsource.MyDynamicSqlSource;
import com.lcl.galaxy.mybatis.frame.sqlsource.MyRawSqlSource;
import com.lcl.galaxy.mybatis.frame.sqlsource.MySqlSource;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyXmlScriptParser {



    private MyConfiguration myConfiguration;
    private boolean isDynamic = false;
    private Map<String, MyNodeHandler> nodeHandlerMap = new HashMap<>();


    public MyXmlScriptParser(MyConfiguration myConfiguration){
        this.myConfiguration = myConfiguration;
        initNodeHandlerMap();
    }

    private void initNodeHandlerMap() {
        nodeHandlerMap.put("if", new MyIfNodeHandler());
        nodeHandlerMap.put("where", new MyWhereNodeHandler());
        nodeHandlerMap.put("foreach", new MyForeachNodeHandler());
    }


    public MySqlSource parseScriptNode(Element element) {
        MyMixedSqlNode rootSqlNode = parseDynamicTags(element);
        MySqlSource mySqlSource = null;
        if(isDynamic){
            mySqlSource = new MyDynamicSqlSource(rootSqlNode);
        }else {
            mySqlSource = new MyRawSqlSource(rootSqlNode);
        }
        return mySqlSource;
    }

    private MyMixedSqlNode parseDynamicTags(Element element) {
        List<MySqlNode> mySqlNodeList = new ArrayList<>();
        for (int i=0; i< element.nodeCount(); i++){
            Node myNode = element.node(i);
            if(myNode instanceof Text){
                String sql = myNode.getText().trim();
                if(sql == null || "".equals(sql)){
                    continue;
                }
                MyTextSqlNode myTextSqlNode = new MyTextSqlNode(sql);
                if (myTextSqlNode.isDynamic()){
                    mySqlNodeList.add(myTextSqlNode);
                    isDynamic = true;
                }else {
                    mySqlNodeList.add(new MyStaticTextSqlNode(sql));
                }
            }else if(myNode instanceof Element){
                Element node2Element = (Element) myNode;
                String nodeName = node2Element.getName().toLowerCase();
                MyNodeHandler myNodeHandler = nodeHandlerMap.get(nodeName);
                myNodeHandler.handleNode(node2Element, mySqlNodeList);
                parseScriptNode(node2Element);
                isDynamic = true;
            }
        }
        return new MyMixedSqlNode(mySqlNodeList);
    }

    private class MyIfNodeHandler implements MyNodeHandler {
        @Override
        public void handleNode(Element node2Element, List<MySqlNode> mySqlNodeList) {
            MyMixedSqlNode myMixedSqlNode = parseDynamicTags(node2Element);
            String test = node2Element.attributeValue("test");
            MyIfSqlNode myIfSqlNode = new MyIfSqlNode(test, myMixedSqlNode);
            mySqlNodeList.add(myIfSqlNode);
        }
    }

    private class MyWhereNodeHandler implements MyNodeHandler {
        @Override
        public void handleNode(Element node2Element, List<MySqlNode> mySqlNodeList) {

        }
    }

    private class MyForeachNodeHandler implements MyNodeHandler {
        @Override
        public void handleNode(Element node2Element, List<MySqlNode> mySqlNodeList) {

        }
    }
}
