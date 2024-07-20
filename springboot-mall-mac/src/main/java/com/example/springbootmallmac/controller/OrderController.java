package com.example.springbootmallmac.controller;

import com.example.springbootmallmac.dto.CreateOrderRequest;
import com.example.springbootmallmac.dto.OrderQueryParams;
import com.example.springbootmallmac.model.Order;
import com.example.springbootmallmac.service.OrderService;
import com.example.springbootmallmac.util.Page;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;

@Validated
@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/users/{userId}/orders")
    public ResponseEntity<Page<Order>> getOrders(
            @PathVariable Integer userId,
            @RequestParam(defaultValue = "5") @Max(1000) @Min(0) Integer limit,
            @RequestParam(defaultValue = "0") @Min(0) Integer offset) {
        OrderQueryParams orderQueryParams = new OrderQueryParams();
        orderQueryParams.setUserId(userId);
        orderQueryParams.setLimit(limit);
        orderQueryParams.setOffset(offset);

        List<Order> orderList = orderService.getOrders(orderQueryParams);
        Integer count = orderService.countOrder(orderQueryParams);

        Page<Order> page = new Page<>();
        page.setLimit(limit);
        page.setOffset(offset);
        page.setTotal(count);
        page.setResults(orderList);

        return ResponseEntity.status(HttpStatus.OK).body(page);
    }

    @PostMapping("/users/{userId}/orders")
    public ResponseEntity<?> createOrder(@PathVariable Integer userId,
                                         @RequestBody @Valid CreateOrderRequest createOrderRequest) {

        Integer orderId = orderService.createOrder(userId, createOrderRequest);

        Order order = orderService.getOrderById(orderId);

        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }
}

