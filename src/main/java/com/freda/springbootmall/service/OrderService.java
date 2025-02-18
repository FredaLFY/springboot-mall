package com.freda.springbootmall.service;

import com.freda.springbootmall.dto.CreateOrderRequest;
import com.freda.springbootmall.model.Order;

public interface OrderService {

    Order getOrderById(Integer orderId);

    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);
}
