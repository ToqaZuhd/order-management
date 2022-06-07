package com.example.orderManagement.dto;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Api(value = "Stock model information")
@Data
public class productOrderDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @ApiModelProperty(value = "Comment quantity")
    private double quantity;

    @NotEmpty
    @ApiModelProperty(value = "Comment price")
    private double price;

    @NotEmpty
    @ApiModelProperty(value = "Comment vat")
    private double vat;

}
