package com.github.fa2bio.api.model;

import com.github.fa2bio.domain.model.Estado;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CidadeModel {

	private Long id;
	private String nome;
	private Estado nomeEstado;
}
