package com.example.orderManagement.service.order;

import com.example.orderManagement.dto.OrderDto;
import com.example.orderManagement.entity.Order;
import com.example.orderManagement.exception.ResourceNotFoundException;
import com.example.orderManagement.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class orderServImp implements orderService{

    private OrderRepository order_Repository;

    public orderServImp(OrderRepository order_Repository) {
        this.order_Repository = order_Repository;
    }
    @Override
    public OrderDto insertOrder(OrderDto order_Dto) {
        Order order = mapToEntity(order_Dto);
        Order newOrder = order_Repository.save(order);

        OrderDto orderResponse = mapToDTO(newOrder);
        return orderResponse;
    }

    @Override
    public List<OrderDto> getAllOrders() {
        List<Order> orders = order_Repository.findAll();
        return orders.stream().map(order -> mapToDTO(order)).collect(Collectors.toList());
    }

    @Override
    public OrderDto getOrderById(int id) {
        Order orders = order_Repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("product", "id", id));
        return mapToDTO(orders);
    }


    @Override
    public OrderDto updateOrder(OrderDto order_Dto, int id) {
        Order order = order_Repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("product", "id", id));

        order.setOrderAt(order_Dto.getOrderAt());


        Order updatedOrder = order_Repository.save(order);
        return mapToDTO(updatedOrder);
    }

    @Override
    public void deleteOrderById(int id) {
        Order orders = order_Repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        order_Repository.delete(orders);
    }


    @Override
    public List<OrderDto> findOrderCertainDate(String date) {
        List<Order> orders = order_Repository.findOrderCertainDate(date);

        return orders.stream().map(order -> mapToDTO(order)).collect(Collectors.toList());
    }
    private OrderDto mapToDTO(Order order){
        OrderDto order_Dto = new OrderDto();
        order_Dto.setId(order.getId());
        order_Dto.setOrderAt(order.getOrderAt());


        return order_Dto;
    }

    private Order mapToEntity(OrderDto order_Dto){
        Order order = new Order();

        order.setOrderAt(order_Dto.getOrderAt());


        return order;

    }
}
