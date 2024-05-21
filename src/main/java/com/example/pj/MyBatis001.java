package com.example.pj;

import com.example.pj.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

public class MyBatis001 {
    public static void main(String[] args) throws Exception {

        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
//        在UserMapper.xml里实现这个函数；UserDao的是给Test用的，不是这个
        List<User> user = sqlSession.selectList("test.findAll");


        System.out.println(user);
    }
}
