package com.example.orderManagement.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.Date;

@Api(value = "Order model information")
@Data
public class OrderDto {

    @ApiModelProperty(value = "Comment id")
    private Integer id;

    @ApiModelProperty(value = "Comment order date")
    @NotEmpty(message = "Order date should not be null or empty")
    private String orderAt;

}
