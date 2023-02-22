package com.test;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

/**
 * @author yangyibufeng
 * @Description
 * @date 2023/2/8-19:11
 */
public class UserServletTest {
    public void login(){
        System.out.println("这是login（）方法调用了");
    }
    public void regist(){
        System.out.println("这是regist（）方法调用了");
    }

    public static void main(String[] args) {
        String action = "login";

        try {
            //获取action业务鉴别字符串，获取相应的业务方法的反射对象
            Method method = UserServletTest.class.getDeclaredMethod(action);

//            System.out.println(method);
            //调用目标方法
            method.invoke(new UserServletTest());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}