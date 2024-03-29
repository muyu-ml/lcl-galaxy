package com.lcl.mybatisnative.sqlnode;

import com.lcl.mybatisnative.config.MyDynamicContext;
import com.lcl.mybatisnative.util.GenericTokenParser;
import com.lcl.mybatisnative.util.OgnlUtils;
import com.lcl.mybatisnative.util.SimpleTypeRegistry;
import com.lcl.mybatisnative.util.TokenHandler;

public class MyTextSqlNode implements MySqlNode{

    private String sql;

    public MyTextSqlNode(String sql) {
        this.sql = sql;
    }

    public boolean isDynamic() {
        if(this.sql.indexOf("${") > -1){
            return true;
        }
        return false;
    }


    @Override
    public void apply(MyDynamicContext context) {
        BingdingTokenHandler bingdingTokenHandler = new BingdingTokenHandler(context);
        GenericTokenParser genericTokenParser = new GenericTokenParser("${", "}", bingdingTokenHandler);
        String parse = genericTokenParser.parse(this.sql);
        context.appendSql(parse);
    }

    private class BingdingTokenHandler implements TokenHandler {

        private MyDynamicContext context;

        public BingdingTokenHandler(MyDynamicContext context) {
            this.context = context;
        }


        @Override
        public String handleToken(String expression) {
            Object paramObject = context.getBingds().get("_param");
            if (paramObject == null) {
                return "";
            } else if (SimpleTypeRegistry.isSimpleType(paramObject.getClass())) {
                return String.valueOf(paramObject);
            }
            // 使用Ognl api去获取相应的值
            Object value = OgnlUtils.getValue(expression, context.getBingds());
            String srtValue = value == null ? "" : String.valueOf(value);
            return srtValue;
        }
    }
}
