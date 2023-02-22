package com.service;

import com.pojo.Cart;

/**
 * @author yangyibufeng
 * @Description
 * @date 2023/2/17-17:28
 */
public interface OrderService {
    public String createOrder(Cart cart ,Integer userId);
}
