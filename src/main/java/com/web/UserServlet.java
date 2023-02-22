package com.web;

import com.google.gson.Gson;
import com.pojo.User;
import com.service.UserService;
import com.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * 处理loginServlet和registServlet业务
 *
 * @author yangyibufeng
 * @date 2023/2/8
 */
//别误导人了，我一句话说不清楚，去看康师傅的java se P278  《子类对象实例化全过程 》   看完视频就知道为什么了。
public class UserServlet extends BaseServlet {
    UserService userService = new UserServiceImpl();

    protected void ajaxExistsUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数username
        String username = req.getParameter("username");
        //调用userService。existsUsername
        boolean existsUsername = userService.existUsername(username);
        //把返回的结果封装成为一个Map对象
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("existUsername",existsUsername);
        //将Map对象转换为JSON
        Gson gson = new Gson();
        String json = gson.toJson(resultMap);
        //将JSON通过响应的字符输出流输出
        resp.getWriter().write(json);

    }
    /**
     * @param req:
     * @param resp:
     * @return void:
     * @author yangyibufeng
     * @description 处理regist事务
     * @date 2023/2/8 19:05
     */
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //首先从kaptcha创建的session域中获取到生成的验证码
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        //然后再将session域中的验证码删除，如果用户重复提交，session域中的token只会是null，这样可以防止用户重复提交
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");
//        2、检查验证码是否正确 == 先写死，为123456（已改为动态验证码）
        if (token != null && token.equalsIgnoreCase(code)) {
            //正确
            if (!userService.existUsername(username)) {
                //3、检查用户名是否可用
//                  可用
//                      调用Service保存到数据库
                userService.registUser(new User(null, username, password, email));
//                      跳到注册成功页面regist_success.html
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);
            } else {
                //                  不可用
                req.setAttribute("msg", "用户名【" + username + "】已存在！");
                req.setAttribute("username", username);
                req.setAttribute("email", email);
                System.out.println("用户名【" + username + "】已存在！");
//                      跳回注册页面
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
            }


        } else {
//                      不正确
            req.setAttribute("msg", "验证码错误!");
            req.setAttribute("username", username);
            req.setAttribute("email", email);
//              跳回注册页面
            System.out.println("验证码【" + code + "】错误");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
        }
    }

    /**
     * @param req:
     * @param resp:
     * @return void:
     * @author yangyibufeng
     * @description 处理login事务
     * @date 2023/2/8 19:05
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User loginUser = userService.login(new User(null, username, password, null));

//        2、调用XxxService.xxx()处理业务
//        userService.login登录
        if (loginUser != null) {
            //3、根据login()方法返回结果判断登录是否成功

//                  成功
//                  跳到成功页面login_success.html
//            System.out.println("success");
//            保存用户登陆之后的信息到session域中
            req.getSession().setAttribute("user", loginUser);

            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
        } else {
            //   失败

//            优化，把错误信息和回显的表单项信息，保存到Request域中
            req.setAttribute("msg", "用户名或密码错误!");
            req.setAttribute("username", username);
//               跳回登录页面
//            System.out.println("false");
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        }
    }

    /**
     * @param req:
     * @param resp:
     * @return void:
     * @author yangyibufeng
     * @description 注销
     * @date 2023/2/15 22:18
     */
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1，销毁session中用户登录的信息（或者销毁session）
        req.getSession().invalidate();
        //2，重定向到首页(或登录页面)。
        resp.sendRedirect(req.getContextPath());
    }
}