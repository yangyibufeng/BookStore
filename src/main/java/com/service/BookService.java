package com.service;

import com.pojo.Book;
import com.pojo.Page;

import java.util.List;

/**
 * @author yangyibufeng
 * @Description
 * @date 2023/2/9-0:50
 */
public interface BookService {
    public void addBook(Book book);

    public void deleteBookById(Integer id);

    public void updateBook(Book book);

    public Book queryBookById(Integer id);

    public List<Book> queryBooks();

    Page<Book> page(int pageNo, int pageSize);

//    Page<Book> page(int pageNo, int pageSize);
//
    Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max);
}
