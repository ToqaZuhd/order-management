package com.example.orderManagement.repository;

import com.example.orderManagement.entity.Customer;
import com.example.orderManagement.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    @Query(value="SELECT * FROM product p where p.price <> :price",nativeQuery = true)
    List<Product> findProductExceptPrice(double price);


    @Query(value="SELECT * FROM product p where p.stockable = 1 and p.id= :pid",nativeQuery = true)
    List<Product> findIsStockable(int pid);

}
