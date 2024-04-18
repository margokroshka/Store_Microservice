package com.university.order.services;

import com.university.order.entity.OrderRequest;
import com.university.order.entity.OrderResponse;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface OrderService {
    OrderResponse createOrder(OrderRequest body);

    List<OrderResponse> getAllOrders();

    OrderResponse getOrderById(Integer id) throws Exception;

    OrderResponse updateOrderById(Integer id, OrderRequest body) throws Exception;

    void deleteById(Integer id) throws Exception;

    List<OrderResponse> getAllOrdersByCustomerId(Integer customerId);

}
