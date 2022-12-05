package com.lcl.mybatisnative.sqlnode.handler;

import com.lcl.mybatisnative.sqlnode.MySqlNode;
import org.dom4j.Element;

import java.util.List;

public interface MyNodeHandler {
    void handleNode(Element node2Element, List<MySqlNode> mySqlNodeList);
}
