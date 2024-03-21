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
    public static Login loginCheck(Login login) throws IOException {
        InputStream stream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(stream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        loginMapper mapper = sqlSession.getMapper(loginMapper.class);
        // 执行sql方法
        Login result = mapper.loginCheck(login.getUser_name(), login.getUser_pwd());

        sqlSession.close();
        return result;
    }

    public static Login getById(Integer id) throws IOException {
        InputStream stream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(stream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        loginMapper mapper = sqlSession.getMapper(loginMapper.class);
        // 执行sql方法
        Login result = mapper.getById(id);

        sqlSession.close();
        return result;
    }

    public static Login getUsername(String username) throws IOException {
        InputStream stream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(stream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        loginMapper mapper = sqlSession.getMapper(loginMapper.class);
        // 执行sql方法
        Login result = mapper.getUsername(username);

        sqlSession.close();
        return result;
    }

    public static Login getIsAdmin(String username) throws IOException {
        InputStream stream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(stream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        loginMapper mapper = sqlSession.getMapper(loginMapper.class);
        // 执行sql方法
        Login result = mapper.getIsAdmin(username);

        sqlSession.close();
        return result;
    }
}
