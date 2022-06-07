package com.example.orderManagement.repository;

import com.example.orderManagement.entity.Customer;
import com.example.orderManagement.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
     @Query(value="SELECT * FROM customer c where c.first_name = :firstName",nativeQuery = true)
    List<Customer> findCustomerFirstName(String firstName);


    @Query(value="SELECT * FROM customer c,`order` o where o.order_at = :date and c.id=o.customer_id",nativeQuery = true)
    List<Customer> GetCustomersByOrderDate(String date);

}
