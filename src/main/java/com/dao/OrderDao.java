package com.dao;

import com.dao.impl.BaseDao;
import com.pojo.Order;

/**
 * @author yangyibufeng
 * @Description
 * @date 2023/2/16-21:39
 */
public interface OrderDao  {
    public int saveOrder(Order order);
}
