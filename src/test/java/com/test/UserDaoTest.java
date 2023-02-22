package com.test;

import com.dao.UserDao;
import com.dao.impl.UserDaoImpl;
import com.pojo.User;
import org.junit.Test;

import javax.xml.transform.Source;

import static org.junit.Assert.*;

/**
 * @author yangyibufeng
 * @Description
 * @date 2023/2/5-19:04
 */
public class UserDaoTest {
    UserDao userDao = new UserDaoImpl();
    @Test
    public void queryUserByUsername() {

        if(userDao.queryUserByUsername("adminqq") != null){
            System.out.println("用户名已存在");
        }else {
            System.out.println("用户名可用");
        }
    }

    @Test
    public void queryUserByUsernameAndPasswd() {
        if(userDao.queryUserByUsernameAndPasswd("admin","admin11") == null){
            System.out.println("用户名或密码错误，查询失败");
        }else{
            System.out.println("查询成功！");
        }
    }

    @Test
    public void saveUser() {
        System.out.println(userDao.saveUser(new User(null,"wzg168","123456","wzg168@qq.com")));
    }
}