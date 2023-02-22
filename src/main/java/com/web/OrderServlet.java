package com.web;

import com.pojo.Cart;
import com.pojo.User;
import com.service.OrderService;
import com.service.impl.OrderServiceImpl;
import com.utils.JdbcUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用来生成订单的程序
 *
 * @author yangyibufeng
 * @date 2023/2/17
 */
public class OrderServlet extends BaseServlet {
    OrderService orderService = new OrderServiceImpl();

    /**
     * @param req:
     * @param resp:
     * @return void:
     * @author yangyibufeng
     * @description 生成订单
     * @date 2023/2/17 22:20
     */
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 先获取Cart购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        // 获取Userid
        User loginUser = (User) req.getSession().getAttribute("user");

        if (loginUser == null) {
            //如果用户还没有登录，就跳转到登录界面
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
            return;
        }
        System.out.println("OrderServlet程序在[" +Thread.currentThread().getName() + "]中");
        Integer userId = loginUser.getId();
        // 调用orderService.createOrder(Cart,UserId);生成订单
        String orderId = null;
        try {
            orderId = orderService.createOrder(cart, userId);

            JdbcUtils.commitAndClose();//提交事务
        } catch (Exception e) {
            JdbcUtils.rollbackAndClose();//回滚事务

            throw new RuntimeException(e);
        }

//        req.setAttribute("orderId",orderId);
//        // 请求转发到/pages/ cart/ checkout.jsp
//        req.getRequestDispatcher("/pages/cart/checkout.jsp").forward(req,resp);
//        防止用户重复提交，修改为重定向
        req.getSession().setAttribute("orderId", orderId);
        resp.sendRedirect(req.getContextPath() + "/pages/cart/checkout.jsp");
    }
}