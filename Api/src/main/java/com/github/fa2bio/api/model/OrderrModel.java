  package com.github.fa2bio.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Relation(collectionRelation = "orders")
public class OrderrModel extends RepresentationModel<OrderrModel>{
	
	@ApiModelProperty(example = "f9981ca4-5a5e-4da3-af04-933861df3e55")
	private String uuiCode;
	
	@ApiModelProperty(example = "298.90")
	private BigDecimal subtotal;
	
	@ApiModelProperty(example = "10.00")
	private BigDecimal rateShipping;
	
	@ApiModelProperty(example = "308.90")
	private BigDecimal totalAmount;
	
	@ApiModelProperty(example = "CREATED")
	private String status;
	
	@ApiModelProperty(example = "2022-12-01T20:34:04Z")
	private OffsetDateTime creationDate; 
	
	@ApiModelProperty(example = "2022-12-01T20:35:10Z")
	private OffsetDateTime confirmationDate;
	
	@ApiModelProperty(example = "2022-12-01T20:55:30Z")
	private OffsetDateTime cancellationDate;
	
	@ApiModelProperty(example = "2022-12-01T20:55:30Z")
	private OffsetDateTime deliveryDate; 
	
	
	private RestaurantAbstractModel restaurant; 
	private UserModel client; 
	private PaymentMethodModel paymentMethod; 
	private AddressModel deliveryAddress; 
	private List<ItemOrderrModel> items; 
}
