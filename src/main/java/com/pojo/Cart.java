package com.pojo;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 购物车对象
 *
 * @author yangyibufeng
 * @date 2023/2/16
 */
public class Cart {
//    private Integer totalCount;
//
//    private BigDecimal totalPrice;

    /**
     * key，是商品编号（唯一）
     * value，是商品信息
     */
    private Map<Integer, CartItem> items = new LinkedHashMap<Integer, CartItem>();
    /**
     * @param cartItem:
     * @return void:
     * @author yangyibufeng
     * @description 添加商品数量
     * @date 2023/2/16 13:26
     */
    public void addItem(CartItem cartItem) {
        //先查看购物车中商品是否已经添加，如果添加过，则数量累加，总金额更新，否则直接放到集合中
        CartItem item = items.get(cartItem.getId());

        if (item == null) {
            //如果没有添加过
            items.put(cartItem.getId(), cartItem);
        } else {
            //已经添加过
            item.setCount(item.getCount() + 1);//数量累加
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));//总金额更新
//            System.out.println(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }
    }

    /**
     * @param id:
     * @return void:
     * @author yangyibufeng
     * @description 删除商品项
     * @date 2023/2/16 13:37
     */
    public void deleteItem(Integer id) {
        items.remove(id);
    }

    /**
     * @return void:
     * @author yangyibufeng
     * @description 清空购物车
     * @date 2023/2/16 13:38
     */
    public void clear() {
        items.clear();
    }

    /**
     * @param id:
     * @param count:
     * @return void:
     * @author yangyibufeng
     * @description 修改商品数量
     * @date 2023/2/16 13:39
     */
    public void updateCount(Integer id, Integer count) {
        //先查看购物车中是否有此商品。如果有，修改商品数量，更新总金额（虽然我也想不明白，为啥要判断，不是只有在购物车中的商品才能进行修改吗？）
        CartItem cartItem = items.get(id);
        if(cartItem != null){
            cartItem.setCount(count);//修改商品数量
            cartItem.setTotalPrice(cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount())));//总金额更新
        }
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }

    public Integer getTotalCount() {
        Integer totalCount = 0;

        for(Map.Entry<Integer,CartItem> entry: items.entrySet()){
            totalCount += entry.getValue().getCount();
        }
        return totalCount;
    }

    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);

        for(Map.Entry<Integer,CartItem> entry: items.entrySet()){
            totalPrice = totalPrice.add(entry.getValue().getTotalPrice());
        }
        return totalPrice;
    }

    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }


}