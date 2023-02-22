package com.web;

import com.pojo.Book;
import com.pojo.Page;
import com.service.BookService;
import com.service.impl.BookServiceImpl;
import com.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 处理首页分页事务
 *
 * @author yangyibufeng
 * @date 2023/2/15
 */
public class ClientBookServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();

    /**
     * @param req:
     * @param resp:
     * @return void:
     * @author yangyibufeng
     * @description 处理分页功能
     * @date 2023/2/13 12:33
     */

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1 获取请求的参数 pageNo 和 pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //2 调用 BookService.page(pageNo，pageSize)：Page 对象
        Page<Book> page = bookService.page(pageNo, pageSize);
        //设置前台跳转的url
        page.setUrl("client/bookServlet?action=page");
        //3 保存 Page 对象到 Request 域中
        req.setAttribute("page", page);
        //4 请求转发到 pages/manager/book_manager.jsp 页面
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
    }

    /**
     * @param req:
     * @param resp:
     * @return void:
     * @author yangyibufeng
     * @description 处理查询价格区间内图书
     * @date 2023/2/15 18:20
     */
    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1 获取请求的参数 pageNo 和 pageSize,min,max
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        int min = WebUtils.parseInt(req.getParameter("min"), 0);
        int max = WebUtils.parseInt(req.getParameter("max"), Integer.MAX_VALUE);
        //2 调用 BookService.page(pageNo，pageSize)：Page 对象
        Page<Book> page = bookService.pageByPrice(pageNo, pageSize, min, max);
        //设置前台跳转的url
        StringBuilder sb = new StringBuilder("client/bookServlet?action=pageByPrice");
        //如果有最小最大价格的参数，才会追加到分页条的地址参数中
        if(req.getParameter("min") != null){
            sb.append("&min=").append(req.getParameter("min"));
        }
        if(req.getParameter("max") != null){
            sb.append("&max=").append(req.getParameter("max"));
        }
        page.setUrl(sb.toString());

//        如果不进行判断的话当用户没有输入min和max就点击查询翻页之后会有数值回显，max会回显成一个很大的数
//        page.setUrl("client/bookServlet?action=pageByPrice&min=" + min +"&max=" + max);
        //3 保存 Page 对象到 Request 域中
        req.setAttribute("page", page);
        //4 请求转发到 pages/manager/book_manager.jsp 页面
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
    }

}