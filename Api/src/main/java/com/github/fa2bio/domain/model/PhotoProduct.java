package com.github.fa2bio.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PhotoProduct {

	@Id
	@EqualsAndHashCode.Include
	@Column(name = "product_id")
	private Long id;
	
	@MapsId
	@OneToOne(fetch = FetchType.LAZY)
	private Product product;
	private String fileName;
	private String description;
	private String contentType;
	private Long size;
	
	public Long getRestaurantId() {
		if(getProduct() != null) {
			return getProduct().getRestaurant().getId();
		}
		return null;
	}
	
}