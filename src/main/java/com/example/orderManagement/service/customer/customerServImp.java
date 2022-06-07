package com.example.orderManagement.service.customer;

import com.example.orderManagement.dto.CustomerDto;
import com.example.orderManagement.entity.Customer;
import com.example.orderManagement.exception.ResourceNotFoundException;
import com.example.orderManagement.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class customerServImp implements customerService {
    private CustomerRepository customer_Repository;

    public customerServImp(CustomerRepository customer_Repository) {
        this.customer_Repository = customer_Repository;
    }
    @Override
    public CustomerDto insertCustomer(CustomerDto customer_Dto) {
        Customer custom = mapToEntity(customer_Dto);
        Customer newCustom = customer_Repository.save(custom);

        CustomerDto customerResponse = mapToDTO(newCustom);
        return customerResponse;
    }

    @Override
    public List<CustomerDto> findCustomerFirstName(String firstName) {
        List<Customer> customers = customer_Repository.findCustomerFirstName(firstName);

        return customers.stream().map(custom -> mapToDTO(custom)).collect(Collectors.toList());
    }


    @Override
    public List<CustomerDto> GetCustomersByOrderDate(String date) {
        List<Customer> customers = customer_Repository.GetCustomersByOrderDate(date);

        return customers.stream().map(custom -> mapToDTO(custom)).collect(Collectors.toList());
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        List<Customer> customs = customer_Repository.findAll();
        return customs.stream().map(custom -> mapToDTO(custom)).collect(Collectors.toList());
    }

    @Override
    public CustomerDto getCustomerById(int id) {
        Customer customs = customer_Repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("product", "id", id));
        return mapToDTO(customs);
    }


    @Override
    public CustomerDto updateCustomer(CustomerDto customer_Dto, int id) {
        Customer custom = customer_Repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("product", "id", id));

        custom.setFirstName(customer_Dto.getFirstName());
        custom.setLastName(customer_Dto.getLastName());
        custom.setBornAt(customer_Dto.getBornAt());


        Customer updatedCustomer = customer_Repository.save(custom);
        return mapToDTO(updatedCustomer);
    }

    @Override
    public void deleteCustomerById(int id) {
        Customer customs = customer_Repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        customer_Repository.delete(customs);
    }

    private CustomerDto mapToDTO(Customer custom){
        CustomerDto customer_Dto = new CustomerDto();
        customer_Dto.setId(custom.getId());
        customer_Dto.setFirstName(custom.getFirstName());
        customer_Dto.setLastName(custom.getLastName());
        customer_Dto.setBornAt(custom.getBornAt());


        return customer_Dto;
    }

    private Customer mapToEntity(CustomerDto customer_Dto){
        Customer custom = new Customer();
        custom.setFirstName(customer_Dto.getFirstName());
        custom.setLastName(customer_Dto.getLastName());
        custom.setBornAt(customer_Dto.getBornAt());


        return custom;

    }
}
