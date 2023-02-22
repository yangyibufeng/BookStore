package com.web;

import com.google.gson.Gson;
import com.pojo.Book;
import com.pojo.Cart;
import com.pojo.CartItem;
import com.service.BookService;
import com.service.impl.BookServiceImpl;
import com.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 处理购物车功能
 *
 * @author yangyibufeng
 * @date 2023/2/16
 */
public class CartServlet extends BaseServlet {

    private BookService bookService = new BookServiceImpl();

    /**
     * @param req:
     * @param resp:
     * @return void:
     * @author yangyibufeng
     * @description 删除商品项
     * @date 2023/2/16 15:30
     */
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取商品编号
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        //获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");

        if (cart != null) {
            //删除相应的数据
            cart.deleteItem(id);
            // 重定向回原来商品列表页面
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    /**
     * @param req:
     * @param resp:
     * @return void:
     * @author yangyibufeng
     * @description 清空购物车
     * @date 2023/2/16 15:48
     */
    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1 获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {
            // 清空购物车
            cart.clear();
            // 重定向回原来购物车展示页面
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    /**
     * @param req:
     * @param resp:
     * @return void:
     * @author yangyibufeng
     * @description 修改商品数量
     * @date 2023/2/16 16:36
     */
    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求的参数 商品编号 、商品数量
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        int count = WebUtils.parseInt(req.getParameter("count"), 1);
//        System.out.println(count);
        // 获取Cart购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");

        if (cart != null) {
            // 修改商品数量
            cart.updateCount(id, count);
            // 重定向回原来购物车展示页面
            resp.sendRedirect(req.getHeader("Referer"));
//            System.out.println(999);
        }
    }

    /**
     * @param req:
     * @param resp:
     * @return void:
     * @author yangyibufeng
     * @description 加入购物车
     * @date 2023/2/16 14:30
     */
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println(999);
//        System.out.println("daole");
        //获取请求的参数商品编号
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        //调用bookService.queryBookById(id ) : Book得到图书的信息
        Book book = bookService.queryBookById(id);
        // 把图书信息，转换成为cartItem商品项
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        //调用cart.addItem(cartItem);添加商品项
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }
        cart.addItem(cartItem);
        //存入最后一个添加的图书名称
        req.getSession().setAttribute("lastName", cartItem.getName());

        // 重定向回原来商品列表页面
        resp.sendRedirect(req.getHeader("Referer"));
//        System.out.println(6666);
    }

    /**
     * @param req:
     * @param resp:
     * @return void:
     * @author yangyibufeng
     * @description 利用ajax技术将部分页面数据回传更新，避免了使用重定向导致的全部页面更新
     * @date 2023/2/19 23:52
     */
    protected void ajaxAddItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println(999);
//        System.out.println("daole");
        //获取请求的参数商品编号
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        //调用bookService.queryBookById(id ) : Book得到图书的信息
        Book book = bookService.queryBookById(id);
        // 把图书信息，转换成为cartItem商品项
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        //调用cart.addItem(cartItem);添加商品项
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }
        cart.addItem(cartItem);
        //存入最后一个添加的图书名称
        req.getSession().setAttribute("lastName", cartItem.getName());

        // 返回购物车总的商品数量和最后一个商品名称
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("totalCount", cart.getTotalCount());
        resultMap.put("lastName", cartItem.getName());
        Gson gson = new Gson();
        String resultMapJsonString = gson.toJson(resultMap);
        resp.getWriter().write(resultMapJsonString);
    }
}