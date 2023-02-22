package com.test;

import com.dao.OrderItemDao;
import com.dao.impl.OrderItemImpl;
import com.pojo.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author yangyibufeng
 * @Description
 * @date 2023/2/17-17:17
 */
public class OrderItemDaoTest {

    @Test
    public void saveOrderItem() {
        OrderItemDao orderItemDao = new OrderItemImpl();

        orderItemDao.saveOrderItem(new OrderItem(null,"java从入门到精通",1,new BigDecimal(100),new BigDecimal(100),"123456"));
        orderItemDao.saveOrderItem(new OrderItem(null,"木须肉盖饭",3,new BigDecimal(100),new BigDecimal(300),"123456"));
        orderItemDao.saveOrderItem(new OrderItem(null,"如何拐走别人老婆",1,new BigDecimal(10),new BigDecimal(10),"123456"));
    }
}