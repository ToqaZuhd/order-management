package com.example.orderManagement.dto;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Api(value = "Stock model information")
@Data
public class StockDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ApiModelProperty(value = "Comment quantity")
    private double quantity;

    @ApiModelProperty(value = "Comment update date")
    private String updateAt;
}
