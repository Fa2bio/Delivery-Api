package com.github.fa2bio.domain.service;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

public interface FotoStorageService {
	
	java.io.InputStream recuperar(String nomeArquivo);

	void armazenar(NovaFoto novaFoto);

	void remover(String nomeArquivo);
	
	default void substituir(String nomeArquivoAntigo, NovaFoto novaFoto) {
		this.armazenar(novaFoto);
		if(nomeArquivoAntigo != null) this.remover(nomeArquivoAntigo);
	}
	
	default String gerarNomeArquivo(String nomeOriginal) {
		return UUID.randomUUID().toString() + "_" + nomeOriginal;
	}
	
	@Builder
	@Getter
	class NovaFoto{
		private String nomeArquivo;
		private java.io.InputStream inputStream;
	}
}
