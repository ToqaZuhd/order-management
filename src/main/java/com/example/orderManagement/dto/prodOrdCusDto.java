package com.example.orderManagement.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Api(value = "Customer model information")
@Data
public class prodOrdCusDto {
    @ApiModelProperty(value = "Comment id")
    private Integer CustomerId;

    @ApiModelProperty(value = "Comment order date")
    @NotEmpty(message = "Order date should not be null or empty")
    private String orderAt;

    @ApiModelProperty(value = "Comment name")
    @NotEmpty(message = "Name should not be null or empty")
    private String name;

    @NotEmpty
    @ApiModelProperty(value = "Comment quantity")
    private int quantity;

    @NotEmpty
    @ApiModelProperty(value = "Comment price")
    private double price;

    @NotEmpty
    @ApiModelProperty(value = "Comment vat")
    private double vat;
}
