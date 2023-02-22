package com.test;

import com.pojo.Cart;
import com.pojo.CartItem;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author yangyibufeng
 * @Description
 * @date 2023/2/16-13:51
 */
public class CartTest {


    @Test
    public void addItem() {
        Cart cart = new Cart();

        cart.addItem(new CartItem(1, "java从入门到精通", 1, new BigDecimal(1000), new BigDecimal(1000)));
        cart.addItem(new CartItem(1, "java从入门到精通", 1, new BigDecimal(1000), new BigDecimal(1000)));
        cart.addItem(new CartItem(2, "数据结构与算法", 1, new BigDecimal(100), new BigDecimal(100)));

        System.out.println(cart);
    }

    @Test
    public void deleteItem() {

        Cart cart = new Cart();

        cart.addItem(new CartItem(1, "java从入门到精通", 1, new BigDecimal(1000), new BigDecimal(1000)));
        cart.addItem(new CartItem(1, "java从入门到精通", 1, new BigDecimal(1000), new BigDecimal(1000)));
        cart.addItem(new CartItem(2, "数据结构与算法", 1, new BigDecimal(100), new BigDecimal(100)));

        System.out.println(cart);
        cart.deleteItem(2);

        System.out.println(cart);
    }

    @Test
    public void clear() {
        Cart cart = new Cart();

        cart.addItem(new CartItem(1, "java从入门到精通", 1, new BigDecimal(1000), new BigDecimal(1000)));
        cart.addItem(new CartItem(1, "java从入门到精通", 1, new BigDecimal(1000), new BigDecimal(1000)));
        cart.addItem(new CartItem(2, "数据结构与算法", 1, new BigDecimal(100), new BigDecimal(100)));

        System.out.println(cart);
        cart.deleteItem(2);

        System.out.println(cart);

        cart.clear();

        System.out.println(cart);
    }

    @Test
    public void updateCount() {
    }
}