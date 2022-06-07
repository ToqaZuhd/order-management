package com.example.orderManagement.controller;

import com.example.orderManagement.dto.OrderDto;
import com.example.orderManagement.exception.BadRequestException;
import com.example.orderManagement.service.order.orderService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;

@Api(value = "CRUD REST APIs for order")
@RestController
@RequestMapping("/api/order")
public class orderController {


    private final Logger log = LoggerFactory.getLogger(orderController.class);


    @Autowired
    private orderService order_service;
    public orderController(orderService order_service) {

        this.order_service = order_service;
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        return ResponseEntity.ok().body(order_service.getAllOrders()); //ResponseEntity represents an HTTP response, including headers, body, and status.
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable(name = "id") int id) {
        return ResponseEntity.ok(order_service.getOrderById(id));
    }



    @GetMapping("/date/{date}")
    public List<OrderDto> findOrderCertainDate(@PathVariable(name = "date") String date) throws ParseException {
        return order_service.findOrderCertainDate(date);
    }

    @PostMapping
    public ResponseEntity<OrderDto> insertOrder(@Valid @RequestBody OrderDto order_Dto) {
        if (order_Dto.getId() != null) {
            log.error("Cannot have an ID {}", order_Dto);
            throw new BadRequestException(orderController.class.getSimpleName(),
                    "Id");
        }
        return new ResponseEntity<>(order_service.insertOrder(order_Dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDto> updateOrder(@Valid @RequestBody OrderDto order_Dto
            , @PathVariable(name = "id") int id) {

        return new ResponseEntity<>(order_service.updateOrder(order_Dto, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable(name = "id") int id) {
        order_service.deleteOrderById(id);
        return new ResponseEntity<>("Deleted successfully.", HttpStatus.OK);
    }
}
