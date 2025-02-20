package com.freda.springbootmall.service;

import com.freda.springbootmall.dto.CreateOrderRequest;
import com.freda.springbootmall.dto.OrderQueryParams;
import com.freda.springbootmall.model.Order;

import java.util.List;

public interface OrderService {

    Integer countOrder(OrderQueryParams orderQueryParams);

    List<Order> getOrders(OrderQueryParams orderQueryParams);

    Order getOrderById(Integer orderId);

    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);
}
