package com.example.orderManagement.service.customer;

import com.example.orderManagement.dto.CustomerDto;
import com.example.orderManagement.entity.Order;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface customerService {

    CustomerDto insertCustomer(CustomerDto customer_Dto);

    List<CustomerDto> getAllCustomers();

    CustomerDto getCustomerById(int id);

    CustomerDto updateCustomer(CustomerDto customer_Dto, int id);

    void deleteCustomerById(int id);

    List<CustomerDto> findCustomerFirstName(String firstName);
    List<CustomerDto> GetCustomersByOrderDate(String date);

}
