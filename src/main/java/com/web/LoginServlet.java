package com.web;

import com.pojo.User;
import com.service.UserService;
import com.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录页面的servlet程序
 *
 * @author yangyibufeng
 * @date 2023/2/6
 */
public class LoginServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        1、获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User login = userService.login(new User(null, username, password, null));

//        2、调用XxxService.xxx()处理业务
//        userService.login登录
        if (login!= null) {
            //3、根据login()方法返回结果判断登录是否成功

//                  成功
//                  跳到成功页面login_success.html
//            System.out.println("success");
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
        } else {
            //   失败

//            优化，把错误信息和回显的表单项信息，保存到Request域中
            req.setAttribute("msg","用户名或密码错误!");
            req.setAttribute("username",username);
//               跳回登录页面
//            System.out.println("false");
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        }



    }
}