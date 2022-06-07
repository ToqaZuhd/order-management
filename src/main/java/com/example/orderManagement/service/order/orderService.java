package com.example.orderManagement.service.order;

import com.example.orderManagement.dto.CustomerDto;
import com.example.orderManagement.dto.OrderDto;

import java.util.Date;
import java.util.List;

public interface orderService {
   OrderDto insertOrder(OrderDto order_Dto);


    List<OrderDto> findOrderCertainDate(String date);
    List<OrderDto> getAllOrders();

    OrderDto getOrderById(int id);

    OrderDto updateOrder(OrderDto order_Dto, int id);

    void deleteOrderById(int id);
}
