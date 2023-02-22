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
import java.util.List;

/**
 * 。
 *
 * @author yangyibufeng
 * @date 2023/2/9
 */
public class BookServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //使每次添加添加图书过后，都会跳转到最后一页，
        // 如果最后一页在添加前没有满，则多跳出的一页因为服务器的校验也会回到最后一页，
        // 如果在添加图书之前最后一页的内容已经满了，就直接再往后多跳一页
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 0);
        pageNo += 1;
        //1.获取请求的参数 并且封装成一个Book对象
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());
        //2.调用BookService.addBook()保存图书
        bookService.addBook(book);
        //3.跳到图书列表页面
//        req.getRequestDispatcher("/manager/bookServlet?action=page").forward(req,resp);
//        上面的是请求转发，当用户按下F5时，浏览器会将记录到的最后一次请求再次转发，所以要用重定向
//        重定向是相当于用到了两次请求，第一次请求是来到服务器进行add请求
//        然后会进行第二次请求，是请求到原来的管理界面,所以按下F5时浏览器只会再次执行跳转到后台管理页面以及list请求
//        而不会执行第一次的add请求
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + pageNo);
        //因为请求转发的/表示到工程名，而重定向的/表示到端口号，所以需要在前面再加上工程名.

    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求的参数id
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        //2.调用book service.deleteBookById删除图书
        bookService.deleteBookById(id);
        //3.重定向会图书列表管理页面
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + req.getParameter("pageNo"));
    }

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//      1，获取请求的参数==封装成为Book对象
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());
//      2，调用BookService.updateBook( book );修改图书
        bookService.updateBook(book);
//      3，重定向回图书列表管理页面
//          地址:/工程名/manager/bookServlet?action=page
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + req.getParameter("pageNo"));

    }

    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//      1.获取请求的参数图书编号
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
//      2.调用bookService.queryBookById查询图书
        Book book = bookService.queryBookById(id);
//      3.保存到图书到Request域中
        req.setAttribute("book", book);
//      4.请求转发到。pages/ manager/book_edit.jsp页面
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req, resp);
    }

    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.通过Book Service查询全部图书
        List<Book> books = bookService.queryBooks();
        //2.把全部图书保存到Request域中
        req.setAttribute("books", books);
        //3.请求转发到/pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
    }

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
        //设置后台跳转的url
        page.setUrl("manager/bookServlet?action=page");
        //3 保存 Page 对象到 Request 域中
        req.setAttribute("page", page);
        //4 请求转发到 pages/manager/book_manager.jsp 页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
    }

}