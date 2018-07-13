package com.mybatis.first;

import com.mybatis.po.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;


import org.apache.ibatis.io.Resources;
import java.io.InputStream;
import java.util.Date;


/**
 * mybatis demo
 */
public class MybatisFirst {

    //创建会话工厂
    private SqlSessionFactory sessionFactory;
    @Before
    public void init() throws Exception{
        //配置文件
        String resource = "SqlMapConfig.xml";
        //加载配置文件
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //创建会话工厂
        sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    //根据id查询用户(得到单条记录)
    @Test
    public void testFindUserById(){
        //通过sessionFactory 创建sqlSession
        SqlSession sqlSession = sessionFactory.openSession();
        //通过sqlSession操作数据库
        User user = null;
        try {
            user = sqlSession.selectOne("test.findUserById",1);
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
        System.out.println(user);
    }

    @Test
    public void testInsertUser(){
        //通过sessionFactory 创建sqlSession
        SqlSession sqlSession = sessionFactory.openSession();
        //通过sqlSession操作数据库
        User user = new User();
        user.setUsername("马家奇");
        user.setAddress("河间");
        user.setSex("1");
        user.setBirthday(new Date());
        try {
            sqlSession.insert("test.deleteUser",user);
            //提价事物
            sqlSession.commit();
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
        System.out.println(user);
    }

    @Test
    public void testDeleteUser(){
        //通过sessionFactory 创建sqlSession
        SqlSession sqlSession = sessionFactory.openSession();
        //通过sqlSession操作数据库

        try {
           sqlSession.delete("test.deleteUser",1);
            //提价事物
            sqlSession.commit();
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }

    }

    @Test
    public void testUpdateUser(){
        //通过sessionFactory 创建sqlSession
        SqlSession sqlSession = sessionFactory.openSession();
        //通过sqlSession操作数据库
        User user = new User();
        user.setUsername("马家奇2");
        user.setAddress("河间3");
        user.setSex("2");
        user.setBirthday(new Date());
        user.setId(27);
        try {
            sqlSession.update("test.updateUser",user);
            //提价事物
            sqlSession.commit();
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }

    }
}
