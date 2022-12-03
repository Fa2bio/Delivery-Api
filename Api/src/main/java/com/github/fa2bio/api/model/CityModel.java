package com.github.fa2bio.api.model;

import com.github.fa2bio.domain.model.State;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CityModel {

	private Long id;
	private String name;
	private State state;
}
