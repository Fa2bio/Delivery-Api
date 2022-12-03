package com.github.fa2bio.domain.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class ItemOrderr {

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private BigDecimal unitPrice;
	private BigDecimal totalPrice;
	private Integer quantity;
	private String note;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Orderr orderr;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Product product;
	
	public void calculateTotalPrice() {
		BigDecimal unitPrice = this.getUnitPrice();
		Integer quantity = this.getQuantity();

		if (unitPrice == null) {
			unitPrice = BigDecimal.ZERO;
		}

		if (quantity == null) {
			quantity = 0;
		}

		this.setTotalPrice(unitPrice);
	}

}
