package com.example.orderManagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.internal.bytebuddy.asm.Advice;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "order")
public class Order {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private int id;

    @Column(name = "order_at", nullable = false)
    private String orderAt;

    /*relation between product order and order, one order will be in many product orders'
     * order references the order attr in productOrder entity
     * */
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<productOrder> productOrders = new HashSet<>();


    //FetchType.Lazy only fetch the related entities from the database you use the relationship.
    //column customerId is foreign key referenced to column id in customer table
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

}
