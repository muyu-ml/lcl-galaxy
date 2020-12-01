package com.lcl.galaxy.mybatis.frame.dao.impl;

import com.lcl.galaxy.mybatis.frame.dao.JdbcUserDao;
import com.lcl.galaxy.mybatis.simple.common.domain.UserDo;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import lombok.extern.slf4j.Slf4j;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class JdbcUserDaoImpl implements JdbcUserDao {


    @Override
    public UserDo findUserById(int id) throws Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        UserDo userDo = new UserDo();
        try {
            //加载数据库驱动
            Class.forName("com.mysql.jdbc.Driver");
            //获取数据库连接
            connection = (Connection) DriverManager.getConnection("jdbc:mysql://********","*******","***********");
            //sql预处理
            String sql = "select * from user where id = ?";
            preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
            //参数设置
            preparedStatement.setInt(1,id);
            //执行sql
            resultSet = preparedStatement.executeQuery();
            //循环结果集
            while (resultSet.next()){
                userDo.setId(resultSet.getInt("id"));
                userDo.setUsername(resultSet.getString("username"));
                userDo.setAddress(resultSet.getString("address"));
                userDo.setSex(resultSet.getString("sex"));
                userDo.setBirthday(resultSet.getDate("birthday"));
                log.info("查询到用户信息，id=【{}】，username=【{}】", resultSet.getString("id"), resultSet.getString("username"));
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //释放资源
            if (resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return userDo;
    }

}
