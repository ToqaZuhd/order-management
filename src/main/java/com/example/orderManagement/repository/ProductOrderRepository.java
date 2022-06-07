package com.example.orderManagement.repository;

import com.example.orderManagement.dto.prodOrdCusDto;
import com.example.orderManagement.entity.CusOrdProd;
import com.example.orderManagement.entity.productOrder;

import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductOrderRepository extends JpaRepository<productOrder, Integer> {
    @Query(value="SELECT po.price FROM product_order po, `order` o where po.order_id=o.id and o.customer_id= :id",nativeQuery = true)
    List<Double> findTotalPriceOfAllOrdersByCustomerId(int id);

    @Query(value="SELECT product_order.quantity as quantity,product_order.price as price,product_order.vat as vat, order.customer_id as customerId, order.order_at as orderAt, product.name as name FROM `order` INNER JOIN product_order ON order.id=product_order.order_id INNER JOIN product ON product.id=product_order.product_id",nativeQuery = true)
    List<CusOrdProd> findAllOrders();


}
