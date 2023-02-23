package com.dao.impl;

import com.dao.OrderDao;
import com.pojo.Order;

/**
 * OrderDao的实现类
 *
 * @author yangyibufeng
 * @date 2023/2/17
 */
public class OrderDaoImpl extends BaseDao implements OrderDao {

    @Override
    public int saveOrder(Order order) {
        String sql = "insert into t_order(`order_id`,`create_time`,`price`,`status`,`user_id`) values (?,?,?,?,?)";

        return update(sql,order.getOrderId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserId());
    }
}