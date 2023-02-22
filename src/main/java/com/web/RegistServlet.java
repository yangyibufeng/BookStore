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
 * 处理注册事件
 *
 * @author yangyibufeng
 * @date 2023/2/6
 */
public class RegistServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        1、获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");
//        2、检查验证码是否正确 == 先写死，为123456
        if ("123456".equalsIgnoreCase(code)) {
            //正确
            if (!userService.existUsername(username)) {
                //3、检查用户名是否可用
//                  可用
//                      调用Service保存到数据库
                userService.registUser(new User(null,username,password,email));
//                      跳到注册成功页面regist_success.html
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);
            } else {
                //                  不可用
                req.setAttribute("msg","用户名【" + username + "】已存在！");
                req.setAttribute("username",username);
                req.setAttribute("email",email);
                System.out.println("用户名【" + username + "】已存在！");
//                      跳回注册页面
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
            }


        } else {
//                      不正确
            req.setAttribute("msg","验证码错误!");
            req.setAttribute("username",username);
            req.setAttribute("email",email);
//              跳回注册页面
            System.out.println("验证码【" + code + "】错误");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
        }


    }
}