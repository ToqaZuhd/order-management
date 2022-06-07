package com.example.orderManagement.controller;

import com.example.orderManagement.dto.prodOrdCusDto;
import com.example.orderManagement.service.productOrder.productOrderService;
import com.example.orderManagement.service.stock.stockService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "CRUD REST APIs for stock")
@RestController
@RequestMapping("/api/productOrder")
public class productOrderController {
    private final Logger log = LoggerFactory.getLogger(productOrderController.class);

    @Autowired
    private productOrderService prodOrder_service;
    public productOrderController(productOrderService prodOrder_service) {

        this.prodOrder_service = prodOrder_service;
    }

    @GetMapping("/{id}")
    public List<Double> findTotalPriceOfAllOrdersByCustomerId(@PathVariable(name = "id")int id) {
        return prodOrder_service.findTotalPriceOfAllOrdersByCustomerId(id);

    }

    @GetMapping
    public List<prodOrdCusDto> findAllOrders() {
        return prodOrder_service.findAllOrders();

    }
}
