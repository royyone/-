/**
 * function:
 * author: suhsbeba
 * date: 2024/2/12 14:49
 */
package com.example.springboot2.Dao;

import com.example.springboot2.mapper.gameMapper;
import com.example.springboot2.pojo.Game;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class gameDao {
    public static Integer insertGame(Game game) throws IOException {
        InputStream stream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(stream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        gameMapper mapper = sqlSession.getMapper(gameMapper.class);
        int result = mapper.insertGame(game.getGame_type(),
                game.getGame_type(),game.getGame_date(), game.getGame_name(), game.getGame_author());

        sqlSession.close();
        return result;
    }
    public static Integer deleteGame(Game game) throws IOException {
        InputStream stream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(stream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        gameMapper mapper = sqlSession.getMapper(gameMapper.class);
        int result = mapper.deleteGame(game.getGame_id());
        mapper.deleteAward(game.getGame_id());// 在award表中删除对应比赛id的获奖项

        sqlSession.close();
        return result;
    }

    public static List<Game> selectGame() throws IOException {
        InputStream stream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(stream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        gameMapper mapper = sqlSession.getMapper(gameMapper.class);
        List<Game> result = mapper.selectGame();

        sqlSession.close();
        return result;
    }

    public static Game selectById(Game game) throws IOException {
        InputStream stream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(stream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        gameMapper mapper = sqlSession.getMapper(gameMapper.class);
        Game result = mapper.selectById(game.getGame_id());

        sqlSession.close();
        return result;
    }
    public static Integer updateStatusById(Game game) throws IOException {
        InputStream stream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(stream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        gameMapper mapper = sqlSession.getMapper(gameMapper.class);
        Integer result = mapper.updateStatusById(game.getGame_id());

        sqlSession.close();
        return result;
    }
}
