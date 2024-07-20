package com.example.springbootmallmac.service;

import com.example.springbootmallmac.dto.CreateOrderRequest;
import com.example.springbootmallmac.dto.OrderQueryParams;
import com.example.springbootmallmac.model.Order;

import java.util.List;

public interface OrderService {
    List<Order> getOrders(OrderQueryParams orderQueryParams);
    Integer countOrder(OrderQueryParams orderQueryParams);
    Order getOrderById(Integer orderId);
    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);
}
