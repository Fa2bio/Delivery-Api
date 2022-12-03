  package com.github.fa2bio.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderrAbstractModel {
	
	@ApiModelProperty(example = "f9981ca4-5a5e-4da3-af04-933861df3e55")
	private String uuiCode;
	
	@ApiModelProperty(example = "298.90")
	private BigDecimal subtotal;
	
	@ApiModelProperty(example = "10.00")
	private BigDecimal rateShipping;
	
	@ApiModelProperty(example = "100.00")
	private BigDecimal totalAmount;
	
	@ApiModelProperty(example = "CREATED")
	private String Status;
	
	@ApiModelProperty(example = "2022-12-01T20:34:04Z")
	private OffsetDateTime creationDate; 
	private RestaurantAbstractModel restaurant; 
	private UserModel Client;
}
