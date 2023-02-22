package com.service;

import com.pojo.User;

/**
 * @author yangyibufeng
 * @Description 表示一个业务层
 * @date 2023/2/6-12:36
 */
public interface UserService {
    /**
     * @param user:
     * @return void:
     * @author yangyibufeng
     * @description 注册用户
     * @date 2023/2/6 12:39
     */
    public void registUser(User user);

    /**
     * @param user:
     * @return com.pojo.User: 返回null 表示登陆失败，返回有值表示登陆成功
     * @author yangyibufeng
     * @description 登录
     * @date 2023/2/6 12:40
     */
    public User login(User user);

    /**
     * @param username:
     * @return boolean:
     * @author yangyibufeng
     * @description 检查用户名是否可用，返回false表示用户名可用，反之不可用
     * @date 2023/2/6 12:41
     */
    public boolean existUsername(String username);
}
