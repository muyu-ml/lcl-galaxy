package com.lcl.galaxy.mybatis.frame.sqlnode.handler;

import com.lcl.galaxy.mybatis.frame.sqlnode.MySqlNode;
import org.dom4j.Element;

import java.util.List;

public interface MyNodeHandler {
    void handleNode(Element node2Element, List<MySqlNode> mySqlNodeList);
}
