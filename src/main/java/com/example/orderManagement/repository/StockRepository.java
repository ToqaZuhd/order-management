package com.example.orderManagement.repository;

import com.example.orderManagement.entity.Customer;
import com.example.orderManagement.entity.Order;
import com.example.orderManagement.entity.Product;
import com.example.orderManagement.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.stereotype.Repository;

import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {

    @Query(value="SELECT s.quantity FROM `stock` s ,`product` p WHERE s.product_id=p.id and p.name = :PName",nativeQuery = true)
    List<Integer> findQuantityProductByName(String PName);

    @Query(value = "SELECT * FROM `stock` WHERE update_at = :dateUp",nativeQuery = true)
    Stock findStockCertainDate(String dateUp);

}
