package com.test;

import com.dao.BookDao;
import com.dao.impl.BookDaoImpl;
import com.pojo.Book;
import com.pojo.Page;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author yangyibufeng
 * @Description
 * @date 2023/2/9-0:45
 */
public class BookDaoTest {

    private BookDao bookDao = new BookDaoImpl();
    @Test
    public void addBook() {
        bookDao.addBook(new Book(null,"国哥为什么这么帅！", "191125", new BigDecimal(9999),1100000,0,null
        ));
    }

    @Test
    public void deleteBookById() {
        bookDao.deleteBookById(21);
    }

    @Test
    public void updateBook() {
        bookDao.updateBook(new Book(21,"大家都可以这么帅！", "国哥", new BigDecimal(9999),1100000,0,null
        ));
    }

    @Test
    public void queryBookById() {
        System.out.println( bookDao.queryBookById(21) );
    }

    @Test
    public void queryBooks() {
        for (Book queryBook : bookDao.queryBooks()) {
            System.out.println(queryBook);
        }
    }

    @Test
    public void queryForPageTotalCount() {
        System.out.println(bookDao.queryForPageTotalCount());
    }

    @Test
    public void queryForPageItems() {
        for (Book item : bookDao.queryForPageItems(8, Page.PAGE_SIZE)) {
            System.out.println(item);
        }
    }

    @Test
    public void queryForPageTotalCountByPrice() {
        System.out.println(bookDao.queryForPageTotalCountByPrice(10,60));
    }

    @Test
    public void queryForPageItemsByPrice() {
        for(Book item : bookDao.queryForPageItemsByPrice(2,Page.PAGE_SIZE,10,70)){
            System.out.println(item);
        }
    }
}