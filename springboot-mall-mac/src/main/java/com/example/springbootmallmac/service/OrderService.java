package com.example.springbootmallmac.service;

import com.example.springbootmallmac.dto.CreateOrderRequest;

public interface OrderService {
    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);
}
