package com.example.orderManagement.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Api(value = "Product model information")
@Data
public class productDto {

    @ApiModelProperty(value = "Comment id")
    private Integer id;

    @ApiModelProperty(value = "Comment name")
    private String name;

    @ApiModelProperty(value = "Comment slug")
    private String slug;

    @ApiModelProperty(value = "Comment reference")
    private String reference;


    @ApiModelProperty(value = "Comment price")
    private double price;

    @ApiModelProperty(value = "Comment vat")
    private double vat;

    @ApiModelProperty(value = "Comment stockable")
    private boolean stockable;
}
