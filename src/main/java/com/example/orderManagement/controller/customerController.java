package com.example.orderManagement.controller;

import com.example.orderManagement.dto.CustomerDto;
import com.example.orderManagement.exception.BadRequestException;
import com.example.orderManagement.service.customer.customerService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "CRUD REST APIs for customer")
@RestController
@RequestMapping("/api/customer")
public class customerController {
    private final Logger log = LoggerFactory.getLogger(customerController.class);


    @Autowired
    private customerService customer_service;
    public customerController(customerService customer_service) {

        this.customer_service = customer_service;
    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        return ResponseEntity.ok().body(customer_service.getAllCustomers()); //ResponseEntity represents an HTTP response, including headers, body, and status.
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable(name = "id") int id) {
        return ResponseEntity.ok(customer_service.getCustomerById(id));
    }



    @PostMapping
    public ResponseEntity<CustomerDto> insertCustomer(@Valid @RequestBody CustomerDto customer_Dto) {
        if (customer_Dto.getId() != null) {
            log.error("Cannot have an ID {}", customer_Dto);
            throw new BadRequestException(customerController.class.getSimpleName(),
                    "Id");
        }
        return new ResponseEntity<>(customer_service.insertCustomer(customer_Dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@Valid @RequestBody CustomerDto customer_Dto
            , @PathVariable(name = "id") int id) {

        return new ResponseEntity<>(customer_service.updateCustomer(customer_Dto, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable(name = "id") int id) {
        customer_service.deleteCustomerById(id);
        return new ResponseEntity<>("Deleted successfully.", HttpStatus.OK);
    }

    @GetMapping("/first/{firstName}")
    public List<CustomerDto> findCustomerByFirstName(@PathVariable(name = "firstName") String firstName) {
        return customer_service.findCustomerFirstName(firstName);
    }


    @GetMapping("/dateOrder/{date}")
    public List<CustomerDto> GetCustomersByOrderDate(@PathVariable(name = "date") String date) {
        return customer_service.GetCustomersByOrderDate(date);
    }
}
