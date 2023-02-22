package com.dao;

import com.pojo.User;

/**
 * @author yangyibufeng
 * @Description 创建接口方便调用
 * @date 2023/2/5-18:22
 */
public interface UserDao {

    /**
     * @author yangyibufeng
     * @description  根据用户名查询用户信息
     * @date 2023/2/5 18:24
     * @param username: 用户名
     * @return com.pojo.User 如果返回null，说明没有这个用户，反之则存在。
     */
    public User queryUserByUsername(String username);

    /**
     * @author yangyibufeng
     * @description  根据用户名和密码查询
     * @date 2023/2/5 18:29
     * @param username:
     * @param passwd:
     * @return com.pojo.User： 如果返回null说明用户名或者密码错误，反之返回用户对象
     */
    public User queryUserByUsernameAndPasswd(String username,String passwd);

    /**
     * @author yangyibufeng 
     * @description  保存用户信息
     * @date 2023/2/5 18:28
     * @param user: 
     * @return int 返回-1表示失败，其他的表示sql语句影响到的行数
     */
    public int saveUser(User user);
}
