package com.github.fa2bio.api.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddressModel {
	private String zipCode;
	private String publicPlace;
	private String number;
	private String complement;
	private String district;
	private CityAbstractModel city;
}