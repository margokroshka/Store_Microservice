package com.university.order.services;

import com.university.order.entity.Order;
import com.university.order.entity.OrderRequest;
import com.university.order.entity.OrderResponse;
import com.university.order.repository.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;

    private final ModelMapper modelMapper;

    public OrderServiceImpl(OrderRepository orderRepository, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public OrderResponse createOrder(OrderRequest body) {
        Order order = modelMapper.map(body, Order.class);
        Order saveOrder = orderRepository.save(order);
        return modelMapper.map(saveOrder, OrderResponse.class);
    }

    @Override
    public List<OrderResponse> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(book -> modelMapper.map(book, OrderResponse.class))
                .toList();
    }

    @Override
    public OrderResponse getOrderById(Integer id) throws Exception {
        Order orders = getOrderByIdFromDB(id);
        return modelMapper.map(orders, OrderResponse.class);
    }

    @Override
    public OrderResponse updateOrderById(Integer id, OrderRequest body) throws Exception {
        return orderRepository.findById(id)
                .map(order -> {
                    Order mapOrder = modelMapper.map(body, Order.class);
                    mapOrder.setId(order.getId());
                    orderRepository.save(mapOrder);
                    return modelMapper.map(orderRepository.save(mapOrder), OrderResponse.class);
                })
                .orElseThrow(() ->
                        new Exception(String.format("Book with ID not Found ", id))
                );
    }

    @Override
    public void deleteById(Integer id) throws Exception {
        Order orders = getOrderByIdFromDB(id);
        orderRepository.deleteById(id);
    }

    @Override
    public List<OrderResponse> getAllOrdersByCustomerId(Integer customerId) {
        return null;
    }

    private Order getOrderByIdFromDB(int id) throws Exception {
        return orderRepository.findById(id).orElseThrow(() ->
                new Exception(
                        String.format("Order wth id not found", id)
                ));
    }
}
