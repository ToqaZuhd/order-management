package com.example.orderManagement.service.productOrder;

import com.example.orderManagement.dto.prodOrdCusDto;
import com.example.orderManagement.entity.CusOrdProd;

import java.util.List;

public interface productOrderService {

    List<Double> findTotalPriceOfAllOrdersByCustomerId(int id);
    List<prodOrdCusDto> findAllOrders();
}
