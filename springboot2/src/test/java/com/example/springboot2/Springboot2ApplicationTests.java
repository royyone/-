package com.example.springboot2;

import com.example.springboot2.mapper.gameMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;

//@SpringBootTest
class Springboot2ApplicationTests {
    @Test
    public void contextLoads() throws IOException {

        System.out.println("hha");
        // 获取核心配置文件的输入流
            InputStream stream = Resources.getResourceAsStream("mybatis-config.xml");
        // 获取SqlSessionFactoryBuilder对象
            SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
            // 获取SqlSessionFactory对象
            SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(stream);
            // 获取sql的会话对象SqlSession（不会自动提交事务）,是Mybatis提供的操作数据的对象
            // SqlSession sqlSession = sqlSessionFactory.openSession();
            // 获取sql的会话对象SqlSession（会自动提交事务）
            SqlSession sqlSession = sqlSessionFactory.openSession(true);
            // 获取UserMapper的代理实现类,
            // 通过getMapper方法，重写接口方法：通过UserMapper的全类名来找到当前对象的映射文件，再通过要调用的方法找到要调用的sql语句

            /**
             * mapper接口和映射文件要保证两个一致:
             *         1，mapper接口的全类名和映射文件的namespace一致
             *         2、mapper接口中的方法的方法名要和映射文件中的sqL的id保持一致
             * */
        Date date = new Date(2023-11-13);
            gameMapper mapper = sqlSession.getMapper(gameMapper.class);
            // 执行sql方法
            int result = mapper.insertGame("二类", "校级",date, "潘志程","潘志程");

            // 接口重写的底层实现： 通过唯一标识找到sql并执行，唯一标识是namespace.sqlId
            // int result = sqlSession.insert("com.fd.mybatis.mapper.UserMapper.insertUser");

            System.out.println("结果：" + result);
            // 需要手动提交事务
            // sqlSession.commit();
            // 关闭会话
            sqlSession.close();
    }
}
