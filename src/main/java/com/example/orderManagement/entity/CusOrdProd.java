package com.example.orderManagement.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
public class CusOrdProd {
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    private int CustomerId;

    private String orderAt;


    private String name;


    private int quantity;


    private double price;

    private double vat;


}
