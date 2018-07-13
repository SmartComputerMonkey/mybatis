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

    //�����Ự����
    private SqlSessionFactory sessionFactory;
    @Before
    public void init() throws Exception{
        //�����ļ�
        String resource = "SqlMapConfig.xml";
        //���������ļ�
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //�����Ự����
        sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    //����id��ѯ�û�(�õ�������¼)
    @Test
    public void testFindUserById(){
        //ͨ��sessionFactory ����sqlSession
        SqlSession sqlSession = sessionFactory.openSession();
        //ͨ��sqlSession�������ݿ�
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
        //ͨ��sessionFactory ����sqlSession
        SqlSession sqlSession = sessionFactory.openSession();
        //ͨ��sqlSession�������ݿ�
        User user = new User();
        user.setUsername("�����");
        user.setAddress("�Ӽ�");
        user.setSex("1");
        user.setBirthday(new Date());
        try {
            sqlSession.insert("test.deleteUser",user);
            //�������
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
        //ͨ��sessionFactory ����sqlSession
        SqlSession sqlSession = sessionFactory.openSession();
        //ͨ��sqlSession�������ݿ�

        try {
           sqlSession.delete("test.deleteUser",1);
            //�������
            sqlSession.commit();
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }

    }

    @Test
    public void testUpdateUser(){
        //ͨ��sessionFactory ����sqlSession
        SqlSession sqlSession = sessionFactory.openSession();
        //ͨ��sqlSession�������ݿ�
        User user = new User();
        user.setUsername("�����2");
        user.setAddress("�Ӽ�3");
        user.setSex("2");
        user.setBirthday(new Date());
        user.setId(27);
        try {
            sqlSession.update("test.updateUser",user);
            //�������
            sqlSession.commit();
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }

    }
}
