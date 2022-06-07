package com.example.orderManagement.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Api(value = "Customer model information")
@Data
public class CustomerDto {

    @ApiModelProperty(value = "Comment id")
    private Integer id;

    @ApiModelProperty(value = "Comment first name")
    @NotEmpty(message = "First Name should not be null or empty")
    private String firstName;

    @ApiModelProperty(value = "Comment last name")
    @NotEmpty(message = "Last Name should not be null or empty")
    private String lastName;

    @ApiModelProperty(value = "Comment born date")
    @NotEmpty(message = "born date should not be null or empty")
    private String bornAt;

}
