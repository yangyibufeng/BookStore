package com.test;

import com.pojo.User;
import com.service.UserService;
import com.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author yangyibufeng
 * @Description
 * @date 2023/2/6-12:48
 */
public class UserServiceTest {

    UserService userService = new UserServiceImpl();
    @Test
    public void registUser() {
        userService.registUser(new User(null,"zhc","123456","zhc@qq.com"));
        userService.registUser(new User(null,"abc168","123456","abc168@qq.com"));
    }

    @Test
    public void login() {
        System.out.println(userService.login(new User(null, "zhc", "123456", "zhc@qq.com")));
    }

    @Test
    public void existUsername() {
        System.out.println(userService.existUsername("zhc") ? "用户名已存在" : "用户名可用");
    }
}