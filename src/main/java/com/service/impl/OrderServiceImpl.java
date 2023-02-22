package com.service.impl;

import com.dao.BookDao;
import com.dao.OrderDao;
import com.dao.OrderItemDao;
import com.dao.impl.BookDaoImpl;
import com.dao.impl.OrderDaoImpl;
import com.dao.impl.OrderItemImpl;
import com.pojo.*;
import com.service.OrderService;
import com.sun.corba.se.impl.resolver.ORBDefaultInitRefResolverImpl;

import java.util.Date;
import java.util.Map;

/**
 * 创建OrderService的实现类
 *
 * @author yangyibufeng
 * @date 2023/2/17
 */
public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemImpl();
    BookDao bookDao = new BookDaoImpl();
    @Override
    public String createOrder(Cart cart, Integer userId) {
        //创建一个订单号 ===》》 唯一
        String orderId = System.currentTimeMillis() + " " + userId;
        //创建一个订单对象
        Order order = new Order(orderId,new Date(),cart.getTotalPrice(),0,userId);
        //保存订单
        orderDao.saveOrder(order);

//        int i = 12 / 0;
        //遍历购物车中的每一项转化成为订单项保存到数据库中
        for(Map.Entry<Integer, CartItem> entry : cart.getItems().entrySet()){
            // 获取每一个购物车中的商品项
            CartItem cartItem = entry.getValue();
            // 转换为每一个订单项
            OrderItem orderItem = new OrderItem(null,cartItem.getName(),cartItem.getCount(),cartItem.getPrice(),cartItem.getTotalPrice(),orderId);
            // 保存订单到数据库
            orderItemDao.saveOrderItem(orderItem);
            //更新库存
            Book book = bookDao.queryBookById(cartItem.getId());
            book.setSales(book.getSales() + cartItem.getCount());
            book.setStock(book.getStock() - cartItem.getCount());
            bookDao.updateBook(book);
        }

        //清空购物车
        cart.clear();

        return orderId;
    }
}