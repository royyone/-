/**
 * function:
 * author: suhsbeba
 * date: 2024/3/7 13:43
 */
package com.example.springboot2.Dao;

import com.example.springboot2.mapper.loginMapper;
import com.example.springboot2.mapper.userMapper;
import com.example.springboot2.pojo.Login;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class userDao {
    public static Integer selectUser(Login login) throws IOException {
        InputStream stream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(stream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        userMapper mapper = sqlSession.getMapper(userMapper.class);
        // 执行sql方法
        Integer result = mapper.selectUser(login.getUser_name());

        sqlSession.close();
        return result;
    }

    public static Integer insertUser(Login login) throws IOException {
        InputStream stream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(stream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        userMapper mapper = sqlSession.getMapper(userMapper.class);
        // 执行sql方法
        Integer result = mapper.insertUser(login.getUser_name(), login.getUser_pwd(), login.getIsadmin(),
                login.getTruth_name(), login.getUser_college());

        sqlSession.close();
        return result;
    }
}
