package com.example.springbootmallmac.service;

import com.example.springbootmallmac.dto.CreateOrderRequest;
import com.example.springbootmallmac.model.Order;

public interface OrderService {
    Order getOrderById(Integer orderId);
    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);
}
