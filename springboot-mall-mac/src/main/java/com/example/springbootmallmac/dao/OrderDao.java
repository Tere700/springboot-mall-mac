package com.example.springbootmallmac.dao;

import com.example.springbootmallmac.dto.OrderQueryParams;
import com.example.springbootmallmac.model.Order;
import com.example.springbootmallmac.model.OrderItem;

import java.util.List;

public interface OrderDao {
    Order getOrderById(Integer orderId);
    List<OrderItem> getOrderItemsByOrderId(Integer orderId);
    List<Order> getOrders(OrderQueryParams orderQueryParams);
    Integer countOrder(OrderQueryParams orderQueryParams);
    Integer createOrder(Integer userId, Integer totalAmount);
    void createOrderItems(Integer orderId, List<OrderItem> orderItemList);
}
