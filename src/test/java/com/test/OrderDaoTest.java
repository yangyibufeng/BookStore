package com.test;

import com.dao.OrderDao;
import com.dao.impl.OrderDaoImpl;
import com.pojo.Order;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * @author yangyibufeng
 * @Description
 * @date 2023/2/17-17:10
 */
public class OrderDaoTest {

    @Test
    public void saveOrder() {
        OrderDao orderDao = new OrderDaoImpl();

        orderDao.saveOrder(new Order("123456",new Date(),new BigDecimal(123),0,1));
    }
}