package com.github.fa2bio.api.model;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemOrderrModel {
	
	@ApiModelProperty(example = "1")
	private Long productId;
	
	@ApiModelProperty(example = "Porco com molho agridoce")
	private String productName;
	
	@ApiModelProperty(example = "2")
	private Integer quantity;
	
	@ApiModelProperty(example = "78.90")
	private BigDecimal unitPrice;
	
	@ApiModelProperty(example = "157.80")
	private BigDecimal totalPrice;
	
	@ApiModelProperty(example = "Menos picante, por favor")
	private String note;
}
