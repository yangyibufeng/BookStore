package com.service.impl;

import com.dao.BookDao;
import com.dao.impl.BookDaoImpl;
import com.pojo.Book;
import com.pojo.Page;
import com.service.BookService;

import java.util.List;

/**
 * @author yangyibufeng
 * @date 2023/2/9
 */
public class BookServiceImpl implements BookService {

    private BookDao bookDao = new BookDaoImpl();

    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void deleteBookById(Integer id) {
        bookDao.deleteBookById(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        Page<Book> page = new Page<Book>();
        //设置每页显示的数量
        page.setPageSize(pageSize);
        //求总记录数
        Integer pageTotalCount = bookDao.queryForPageTotalCount();
        //设置总记录数
        page.setPageTotalCount(pageTotalCount);
        //求总页码
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {
            pageTotal += 1;
        }
        //设置总页码
        page.setPageTotal(pageTotal);

        //设置当前页码
        page.setPageNo(pageNo);
//        System.out.println(pageTotal);
//        System.out.println(pageNo);
//        设置过
        pageNo = page.getPageNo();

        //求当前页索引
        int begin = (pageNo - 1) * pageSize;
        //求当前页数据
        List<Book> items = bookDao.queryForPageItems(begin, pageSize);
        //设置当前页数据
        page.setItems(items);
        return page;
    }

    /**
     * @author yangyibufeng 
     * @description  处理利用价格区间查询
     * @date 2023/2/15 18:16
     * @param pageNo: 
 * @param pageSize: 
 * @param min: 
 * @param max: 
     * @return com.pojo.Page<com.pojo.Book>:
     */
    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max) {
        Page<Book> page = new Page<Book>();
        //设置每页显示的数量
        page.setPageSize(pageSize);
        //求总记录数
        Integer pageTotalCount = bookDao.queryForPageTotalCountByPrice(min,max);
        //设置总记录数
        page.setPageTotalCount(pageTotalCount);
        //求总页码
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {
            pageTotal += 1;
        }
        //设置总页码
        page.setPageTotal(pageTotal);

        //设置当前页码
        page.setPageNo(pageNo);
//        System.out.println(pageTotal);
//        System.out.println(pageNo);
//        设置过
        pageNo = page.getPageNo();

        //求当前页索引
        int begin = (pageNo - 1) * pageSize;
        //求当前页数据
        List<Book> items = bookDao.queryForPageItemsByPrice(begin, pageSize,min,max);
        //设置当前页数据
        page.setItems(items);
        return page;
    }
}