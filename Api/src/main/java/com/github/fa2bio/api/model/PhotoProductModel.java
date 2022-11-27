package com.github.fa2bio.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhotoProductModel {
	private String nomeArquivo;
	private String descricao;
	private String contentType;
	private Long tamanho;
}
