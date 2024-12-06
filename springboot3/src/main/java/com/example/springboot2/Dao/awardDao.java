/**
 * function:
 * author: suhsbeba
 * date: 2024/2/13 14:05
 */
package com.example.springboot2.Dao;

import com.example.springboot2.mapper.awardMapper;
import com.example.springboot2.pojo.Certificate;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class awardDao {
    public static Integer insertAward(Certificate certificate) throws IOException {
        InputStream stream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(stream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        awardMapper mapper = sqlSession.getMapper(awardMapper.class);
        int result = mapper.insertAward(certificate.getGame_id(),
                certificate.getCollege_name(), certificate.getStu_name(), certificate.getAward_team(),
                certificate.getAward(), certificate.getFile_path(), certificate.getAdviser(),
                certificate.getWork_name()
                );

        sqlSession.close();
        return result;
    }
    public static List<Certificate> selectAward(Certificate certificate) throws IOException {
        InputStream stream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(stream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        awardMapper mapper = sqlSession.getMapper(awardMapper.class);
        List<Certificate> result = mapper.selectAward(certificate.getGame_id());

        sqlSession.close();
        return result;
    }
    public static Integer updateAward(Certificate certificate) throws IOException {
        InputStream stream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(stream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        awardMapper mapper = sqlSession.getMapper(awardMapper.class);
        Integer result = mapper.updateAward(certificate.getAward_id(), certificate.getStatus(), certificate.getFile_path());

        sqlSession.close();
        return result;
    }

    public static List<Certificate> stuSelect(Certificate certificate) throws IOException {
        InputStream stream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(stream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        awardMapper mapper = sqlSession.getMapper(awardMapper.class);
        List<Certificate> result = mapper.stuSelect(certificate.getStu_name());

        sqlSession.close();
        return result;
    }
}
