package com.dao.impl;

import com.dao.OrderItemDao;
import com.pojo.OrderItem;

/**
 * OrderItem的实现类
 *
 * @author yangyibufeng
 * @date 2023/2/17
 */
public class OrderItemImpl extends BaseDao implements OrderItemDao {

    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql = "insert into t_order_item(`name`,`count`,`price`,`total_price`,`order_id`) values (?,?,?,?,?)";

        return update(sql,orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),orderItem.getTotalPrice(),orderItem.getOrderId());
    }
}