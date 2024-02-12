/**
 * function:
 * author: suhsbeba
 * date: 2024/2/12 15:40
 */
package com.example.springboot2.Dao;

import com.example.springboot2.mapper.loginMapper;
import com.example.springboot2.pojo.Login;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class loginDao {
    public static Integer loginCheck(Login login) throws IOException {
        InputStream stream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(stream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        loginMapper mapper = sqlSession.getMapper(loginMapper.class);
        // 执行sql方法
        Integer result = mapper.loginCheck(login.getUsername(), login.getPassword());

        sqlSession.close();
        return result;
    }
}
