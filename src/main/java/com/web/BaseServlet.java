package com.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * 用于被其他的servlet类继承 实现代码复用
 *
 * @author yangyibufeng
 * @date 2023/2/8
 */
public abstract class BaseServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8"); //1
        resp.setContentType("text/html;charset=utf-8"); //2
        resp.setCharacterEncoding("utf-8"); //3


        String action = req.getParameter("action");
        //利用反射优化if-else
        try {
            //获取action业务鉴别字符串，获取相应的业务方法的反射对象
            Method method = this.getClass().getDeclaredMethod(action,HttpServletRequest.class,HttpServletResponse.class);

//            System.out.println(method);
            //调用目标方法
            method.invoke(this,req,resp);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);//把异常抛给filter过滤器
        }
//        if (action.equals("login")) {
//            login(req, resp);
//        } else if (action.equals("regist")) {
//            regist(req, resp);
//        }
//        System.out.println("base");

    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}