package com.example.orderManagement.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table (name = "product")
public class Product {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private int id;

    @Column(name = "slug", nullable = false)
    private String slug;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "reference", nullable = false)
    private String reference;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "vat", nullable = false)
    private double vat;

    @Column(name = "stockable", nullable = false)
    private boolean stockable;

    /*relation between product and stock, one product has many stocks'
     * product references the product attr in stock entity
     * */
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Stock> stocks = new HashSet<>();

    /*relation between product and product orders, one product will be in many orders'
     * product references the product attr in productOrder entity
     * */
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<productOrder> product_Orders = new HashSet<>();

}
